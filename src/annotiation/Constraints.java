package annotiation;
import java.lang.annotation.*;
 
@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
 
public @interface Constraints {
    boolean primaryKey() default false;   // ������Ĭ��Ϊ��
    boolean allowNull() default true;     // Ĭ������Ϊ��
    boolean unique() default false;          // Ĭ�������ظ�
    boolean auto() default false; //������Ĭ�Ϸ�
    boolean foreignKey() default false; //�Ƿ����
}