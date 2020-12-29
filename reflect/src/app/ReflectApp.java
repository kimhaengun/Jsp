package app;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import dto.JoinDto;
import dto.LoginDto;

public class ReflectApp {

	static <T>void myRlect(T dto) {
		Method[] methods = dto.getClass().getMethods();
		for (Method method : methods) {
//			System.out.println(method.getName());
		}
		
		Field[] fs = dto.getClass().getDeclaredFields();
		for (Field f : fs) {
			f.setAccessible(true);
			try {
				if(f.getName().equals("password")) {
					f.set(dto, "5678");
				}
				Object o = f.get(dto);
				System.out.println(o);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public static void main(String[] args) {
		LoginDto loginDto = new LoginDto();
		loginDto.setUsername("ssar");
		loginDto.setPassword("1234");
		
		JoinDto joindto = new JoinDto();
		joindto.setUsername("ssar");
		joindto.setPassword("5555");
		joindto.setEmail("ssar@nate.com");
		
		myRlect(loginDto);
		myRlect(joindto);

	}// main 끝

}//클래스 끝
