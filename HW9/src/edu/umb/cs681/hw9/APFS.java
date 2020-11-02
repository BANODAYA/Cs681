package edu.umb.cs681.hw9;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class APFS extends FileSystem implements Runnable {

	private String ownerName;
	private LocalDateTime lastModified;
	private static APFS instance = null;

	public APFS(String ownerName) {
		this.ownerName = ownerName;
		this.lastModified = LocalDateTime.now();
	}

	public APFS getInstance() {
		if (instance == null) {
			instance = new APFS(ownerName);
		}
		return instance;
	}

	@Override
	protected FSElement createDefaultRoot() {
		return new ApfsDirectory(null, "root");
	}

	public ApfsDirectory getRootDir() {
		ApfsDirectory root = (ApfsDirectory) this.getRoot();
		return root;
	}

	public void setRootDir(ApfsDirectory root) {
		super.setRoot(root);
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public LocalDateTime getLastModified() {
		return this.lastModified;
	}

	public void run() {
		System.out.println("\nThread #" + Thread.currentThread().getId());
		System.out.println("Size of " + getRootDir().getName() + " dir: " + getRootDir().getTotalSize());
		LinkedList<ApfsElement> rootChildren = getRootDir().getChildren();
		for (ApfsElement child : rootChildren) {
			System.out.println("\n"+child.getName());
			LinkedList<ApfsElement> child1Children =child.getChildren();
			for (ApfsElement child1 : child1Children) {
				System.out.println(child1.getName());
				LinkedList<ApfsElement> child2Children =child1.getChildren();
				for (ApfsElement child2 : child2Children) {
					System.out.println(child2.getName());
				}
			}
		}
	}

	public static void main(String args[]) {

		APFS apfs = new APFS("File System of APFS");
		

		apfs.initFileSystem("Downloads", 2000);
		ApfsDirectory root = apfs.getRootDir();

		ApfsDirectory applications = new ApfsDirectory(root, "applications");
		root.appendChild(applications);
		ApfsFile b,n;
		b = new ApfsFile(applications, "b", 30);
		n = new ApfsFile(applications, "n", 10);
		applications.appendChild(b);
		applications.appendChild(n);

		Thread t1 = new Thread(apfs);
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ApfsDirectory home  = new ApfsDirectory(root, "home");
		root.appendChild((home));
		ApfsFile b2, n2;
		b2 = new ApfsFile(home, "b2", 10);
		n2 = new ApfsFile(home, "n2", 20);
		home.appendChild(b2);
		home.appendChild(n2);
		
		ApfsDirectory code = new ApfsDirectory(home, "code");
		home.appendChild(code);
		

		Thread t2 = new Thread(apfs);
		
		try {
			t2.start();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		

		ApfsLink a, z;
		a = new ApfsLink(home, "a", applications);
		z = new ApfsLink(code, "z", b);
		home.appendChild(a);
		code.appendChild(z);

		Thread t3 = new Thread(apfs);
		try {
			t3.start();
			t3.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("Root total size: " + root.getTotalSize());
		LinkedList<ApfsElement> rootChildren = root.getChildren();
		for (ApfsElement child : rootChildren) {
			System.out.println(child.getName());
		}
	}
}