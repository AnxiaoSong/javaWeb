package annotiation;


import java.lang.annotation.*;
 
@Inherited
@Documented
@Target(ElementType.FIELD)              // ������������ö������ʵ����
@Retention(RetentionPolicy.RUNTIME)
 
public @interface SQLInteger {
    String name() default "";
    Constraints constraints() default @Constraints;//Լ������
	String size();
}

