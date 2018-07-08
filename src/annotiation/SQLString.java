package annotiation;

import java.lang.annotation.*;
 
@Inherited
@Documented
@Target(ElementType.FIELD)              // 域声明（包括枚举类型实例）
@Retention(RetentionPolicy.RUNTIME)
 
public @interface SQLString {
    String name() default "";
    Constraints constraints() default @Constraints;
	String size(); 
}// 约束注解，详细见下面代码
