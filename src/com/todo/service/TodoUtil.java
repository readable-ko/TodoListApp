package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[�׸� �߰�] \n"
				+ "���� > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("������ �ߺ��˴ϴ�!");
			sc.close();
			return;
		}
		
		System.out.println("���� > ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("������ �߰��Ǿ����ϴ�.");
		sc.close();
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		
		System.out.println("[�׸� ����]\n"
				+ "������ �׸��� ������ �Է��Ͻÿ� > ");
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("�����Ǿ����ϴ�.");
				break;
			}
		}
		sc.close();
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[�׸� ����]\n"
				+ "������ �׸��� ������ �Է��Ͻÿ� > ");
		
		String title = sc.nextLine().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("�������� �ʴ� �����Դϴ�!");
			sc.close();
			return;
		}

		System.out.println("�� ���� > ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�ߺ��� �����Դϴ�!");
			sc.close();
			return;
		}
		
		System.out.println("�� ���� > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("�����Ǿ����ϴ�.");
			}
		}
		sc.close();
	}

	public static void listAll(TodoList l) {
		System.out.println("[��ü ���]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			w.write(l.toString());
			System.out.println("������ ���� �Ϸ�!!");
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void loadList(TodoList l, String filename) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String temp;
			while((temp = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(temp,"##");
				String title = st.nextToken();
				String desc = st.nextToken();
				String date = st.nextToken();
				
				TodoItem item = new TodoItem(title,desc);
				item.setCurrent_date(date);
			}
			br.close();
			System.out.println("������ �ε� �Ϸ�!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
