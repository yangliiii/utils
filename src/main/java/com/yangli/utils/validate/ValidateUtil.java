package com.yangli.utils.validate;

import org.hibernate.validator.util.LazyValidatorFactory;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * lies, please leave something
 * 调用org.springframework.validation 校验
 * @author lies
 * @Createdon 2017/12/20 10:05.
 * @ProjectName utils
 */
public class ValidateUtil {

    /**
     * 调用org.springframework.validation 校验
     * @param t
     * @param <T>
     * @return
     */
    public <T> List<String> validateAnnotation(T t){
        ValidatorFactory factory = new LazyValidatorFactory();
        Validator validator = factory.getValidator();
        //校验满足约束条件的集合
        Set<ConstraintViolation<T>> result = validator.validate(t);
        List<String> msg = new ArrayList<String>();
        //如果不为空，则校验不通过。
        if (!CollectionUtils.isEmpty(result)) {
            //进行遍历取出校验不通过的数据,通过Iterator进行遍历
            Iterator<ConstraintViolation<T>> iterator = result.iterator();
            //通过while循环遍历
            while (iterator.hasNext()) {
                //逐条取出校验不通过的内容
                ConstraintViolation<T> violation = iterator.next();
                msg.add(violation.getPropertyPath().toString() + "：" + violation.getMessage());
            }
        }
        return msg;
    }

}
