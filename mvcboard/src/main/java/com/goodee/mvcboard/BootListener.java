package com.goodee.mvcboard;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import color.Color;

@WebListener
public class BootListener implements ServletContextListener {
	String RESET = Color.ANSI_RESET;
	String GREEN = Color.ANSI_GREEN_BACKGROUND;
    
	public BootListener() {
		
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
         System.out.println(GREEN+"모든 요청을 가로채서 UTF-8 인코딩 실행하는 코드"+RESET);
    }

    public void contextInitialized(ServletContextEvent sce)  { 
         System.out.println(GREEN+"톰캣 시작시 실행되는 코드"+RESET);
    }
	
}
