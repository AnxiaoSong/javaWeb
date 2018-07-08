package annotiation;

import java.lang.annotation.*;
 
@Inherited
@Documented
@Target(ElementType.FIELD)              // ������������ö������ʵ����
@Retention(RetentionPolicy.RUNTIME)
 
public @interface SQLString {
    String name() default "";
    Constraints constraints() default @Constraints;
	String size(); 
}// Լ��ע�⣬��ϸ���������
