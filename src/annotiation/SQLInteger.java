package annotiation;


import java.lang.annotation.*;
 
@Inherited
@Documented
@Target(ElementType.FIELD)              // 域声明（包括枚举类型实例）
@Retention(RetentionPolicy.RUNTIME)
 
public @interface SQLInteger {
    String name() default "";
    Constraints constraints() default @Constraints;//约束条件
	String size();
}

