package com.xingying.shopping.master.common.utils.token;

import com.sun.el.parser.Token;
import com.xingying.shopping.master.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author SiletFlower
 * @date 2021/4/9 10:04:11
 * @description
 */
@Component
public class JwtTokenUtil {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    /**
     * 秘钥
     */
    @Value("${token.secret}")
    private String secret;
    /**
     * 过期时间
     */
    @Value("${token.expireTime}")
    private Long expireTime;
    /**
     * 存放token的header
     */
    @Value("${token.header}")
    private String tokenName;
    /**
     * 存放token的head
     */
    @Value("${token.head}")
    private String head;

    public String getTokenName() {
        return tokenName;
    }


    public String getHead() {
        return head;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    /**
     * 修改密码后踢出用户
     */
    public boolean makeTokenExpireByUid(String uid){
        Set<String> keys = redisTemplate.keys("*#" + uid);
        Long delete = redisTemplate.delete(keys);
        if (delete > 0) {
            return true;
        }
        return false;
    }

    /**
     * 从前端的header或者cookie中读取token
     */
    public String getTokenFromFront(HttpServletRequest request) {
        String jwtToken = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (getTokenName().equals(cookie.getName())) {
                    jwtToken = cookie.getValue();
                }
            }
        }
        if (jwtToken == null) {
            jwtToken = request.getHeader(getTokenName());
        }
        return jwtToken;
    }

    /**
     * 根据用户信息生成token，并将token#id和用户信息存入redis
     */
    public String generateToken(String uid, UserEntity userEntity) {
        String token = Jwts.builder()
                .setSubject(uid)
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        //将token#id存入redis
        if(redisSet(token+"#"+uid,userEntity)){
            return token;
        }else {
            return null;
        }
    }

    /**
     * 根据tokenAndId获取redis存储的属性
     */
    public UserEntity getUserByToken(String token,String uid){
        UserEntity user = redisGet(token+"#"+uid);
        return user;
    }

    /**
     * 通过jwtToken获取uid
     */
    public String getuidByToken(String jwtToken) {
        try {
            String token = jwtToken.replace(head, "");
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 延长登陆时效
     */
    public boolean tokenExtend(String token, String uid) {
        return redisExtend(token + "#" + uid);
    }



    /**
     * redis进行存放
     */
    private boolean redisSet(String key, UserEntity value) {
        try {
            redisTemplate.opsForValue().set(key, value,expireTime/1000, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * redis进行获取
     * @param key
     * @return
     */
    private UserEntity redisGet(String key) {
        return key == null ? null : (UserEntity) redisTemplate.opsForValue().get(key);
    }

    /**
     * redis进行删除
     * @param key
     * @return
     */
    public boolean redisDel(String key) {
        try {
            //模糊删除
            redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * redis 延长某个key的ttl
     */
    private boolean redisExtend(String key) {
        try {
            Long expire = redisTemplate.getExpire(key);
            //获取某个key的时长如果小于12小时那么自动续期1天
            if (expire <= 60 * 60 * 12) {
                redisTemplate.expire(key,60*60*24+expire,TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
