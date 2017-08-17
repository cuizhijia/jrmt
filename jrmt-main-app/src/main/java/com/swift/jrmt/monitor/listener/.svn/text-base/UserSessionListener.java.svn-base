package com.swift.jrmt.monitor.listener;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserSessionListener implements HttpSessionListener {
	private HashSet<HttpSession> hashSet = new HashSet<>();

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		addSession(session);
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		removeSession(session);
	}

	private synchronized void addSession(HttpSession session) {
		hashSet.add(session);
	}

	private synchronized void removeSession(HttpSession session) {
		hashSet.remove(session);
	}

	@SuppressWarnings("unused")
	private void lookToSys() {
		while (true) {
			System.out.println(hashSet.size());
			try {
				TimeUnit.MINUTES.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}