package com.worldpay.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
	public static SessionFactory getSF(){
		Configuration config=new Configuration().configure();
		SessionFactory sf=config.buildSessionFactory();
		return sf;
	}
}
