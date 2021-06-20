package com.zhong.Listen;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class MyListener
 *
 */
@WebListener
public class MyListener implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener,
		HttpSessionAttributeListener {

	/**
	 * Default constructor.
	 */
	private ServletContext app;

	public MyListener() {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().setAttribute("allUsers", new HashSet<String>());
		this.app = sce.getServletContext();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	/**
	 * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
	 */
	public void attributeAdded(ServletContextAttributeEvent scae) {
		System.err.println("【APPLICATION 增加属性】name = " + scae.getName() + "，value = " + scae.getValue());
	}

	/**
	 * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
	 */
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		System.err.println("【APPLICATION 删除属性】name = " + scae.getName() + "，value = " + scae.getValue());
	}

	/**
	 * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
	 */
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		System.err.println("【APPLICATION 替换属性】name = " + scae.getName() + "，value = " + scae.getValue());
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.err.println("【SESSION 创建】" + se.getSession().getId());
	}

	@Override		
	public void sessionDestroyed(HttpSessionEvent se) {
		System.err.println("【SESSION 销毁】" + se.getSession().getId());
		Set<String> all = (Set<String>) this.app.getAttribute("allUsers");
		all.remove(se.getSession().getAttribute("userid"));
		this.app.setAttribute("allUsers", all);
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		Set<String> all = (Set<String>) this.app.getAttribute("allUsers");
		all.add(se.getValue().toString());
		this.app.setAttribute("allUsers", all);
		System.out.println("***********************"+se.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("【删除属性】id=" + se.getSession().getId() + ",name" + se.getName() + ",value=" + se.getValue());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("【替换属性】id=" + se.getSession().getId() + ",name" + se.getName() + ",value=" + se.getValue());
	}
}
