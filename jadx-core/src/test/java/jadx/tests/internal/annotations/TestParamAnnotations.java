package jadx.tests.internal.annotations;

import jadx.api.InternalJadxTest;
import jadx.core.dex.nodes.ClassNode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class TestParamAnnotations extends InternalJadxTest {

	public static class TestCls {

		@Target({ElementType.PARAMETER})
		@Retention(RetentionPolicy.RUNTIME)
		public static @interface A {
			int i() default 7;
		}

		void test1(@A int i) {
		}

		void test2(int i, @A int j) {
		}

		void test3(@A(i = 5) int i) {
		}
	}

	@Test
	public void test() {
		ClassNode cls = getClassNode(TestCls.class);
		String code = cls.getCode().toString();
		System.out.println(code);

		assertThat(code, containsString("void test1(@A int i) {"));
		assertThat(code, containsString("void test2(int i, @A int j) {"));
		assertThat(code, containsString("void test3(@A(i = 5) int i) {"));
	}
}
