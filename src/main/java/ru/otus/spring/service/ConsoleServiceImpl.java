package ru.otus.spring.service;

import java.io.PrintStream;

import org.springframework.stereotype.Service;

@Service
public class ConsoleServiceImpl implements ConsoleService {
	
	private static final PrintStream OUT = System.out;
	
	public void write(String msg, Object... args) {
		OUT.print("> ");
		OUT.print(ANSI_GREEN);
		OUT.printf(msg, args);
		OUT.print(ANSI_RESET);
		OUT.println();
	}
	
}
