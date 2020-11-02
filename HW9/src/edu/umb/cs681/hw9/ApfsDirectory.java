package edu.umb.cs681.hw9;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class ApfsDirectory extends ApfsElement {

	private LinkedList<ApfsElement> children;
	private LinkedList<ApfsFile> files = new LinkedList<ApfsFile>();
	private LinkedList<ApfsDirectory> myDirectory = new LinkedList<ApfsDirectory>();

	public ApfsDirectory(ApfsDirectory parent, String name) {
		super(parent, name, 0, LocalDateTime.now());
		this.children = new LinkedList<>();
	}

	@Override
	public boolean isDirectory() { 
		return true; 
		}

	@Override
	public boolean isFile() {
		return false; 
		}

	@Override
	public boolean isLink() {
		return false;
		}

	public LinkedList<ApfsElement> getChildren() { 
		return this.children; 
		}

	public void appendChild(ApfsElement child) {
		this.children.add(child);
		child.setParent(this);
	}

	public int countChildren() { 
		return this.children.size(); 
		}

	public LinkedList<ApfsDirectory> getSubDirectories() {
		for (ApfsElement element: this.children) {
			if (element.isDirectory()) {
				myDirectory.add((ApfsDirectory) element);
			}
		}
		return myDirectory;
	}

	public LinkedList<ApfsFile> getFiles() {
		for (ApfsElement element: this.children) {
			if (element.isFile()) {
				files.add((ApfsFile) element);
			}
		}
		return files;
	}

	public int getTotalSize() {
		int totalS = 0;
		for (ApfsElement element : this.children) {
			if (element.isDirectory()) {
				ApfsDirectory myDirectory = (ApfsDirectory) element;
				totalS += myDirectory.getTotalSize();

			} else if (element.isFile()) {
				totalS += element.getSize();
			}

		}
		return totalS;
	}
}
