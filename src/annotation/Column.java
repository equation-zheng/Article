package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.FIELD)// ע���Ŀ��
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

	public String field();// �ֶ�����

	public boolean primaryKey() default false;// �Ƿ�Ϊ����

	public String type() default "VARCHAR(80)";// �ֶ�����

	public boolean defaultNull() default true;// �Ƿ�����Ϊ��

}