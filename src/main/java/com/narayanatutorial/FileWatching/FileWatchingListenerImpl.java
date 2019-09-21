package com.narayanatutorial.FileWatching;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class FileWatchingListenerImpl implements FileAlterationListener {

	//execute the method every time when schedule triggered
	public void onStart(final FileAlterationObserver observer) {
		System.out.println("The FileListener has started on " + observer.getDirectory().getAbsolutePath());
	}

	//execute the method every time when schedule triggered and when directory created
	public void onDirectoryCreate(final File directory) {
		System.out.println(directory.getAbsolutePath() + " was created.");
	}

	//execute the method every time when schedule triggered and when directory modified
	public void onDirectoryChange(final File directory) {
		System.out.println(directory.getAbsolutePath() + " wa modified");
	}


	//execute the method every time when schedule triggered and when directory deleted
	public void onDirectoryDelete(final File directory) {
		System.out.println(directory.getAbsolutePath() + " was deleted.");
	}

	//execute the method every time when schedule triggered and when file created
	public void onFileCreate(final File file) {
		System.out.println(file.getAbsoluteFile() + " was created.");
		System.out.println("1. length: " + file.length());
		System.out.println("2. last modified: " + new Date(file.lastModified()));
		System.out.println("3. readable: " + file.canRead());
		System.out.println("4. writable: " + file.canWrite());
		System.out.println("5. executable: " + file.canExecute());
	}

	//execute the method every time when schedule triggered and when file modified
	public void onFileChange(final File file) {
		System.out.println(file.getAbsoluteFile() + " was modified.");
		System.out.println("1. length: " + file.length());
		System.out.println("2. last modified: " + new Date(file.lastModified()));
		System.out.println("3. readable: " + file.canRead());
		System.out.println("4. writable: " + file.canWrite());
		System.out.println("5. executable: " + file.canExecute());
	}

	//execute the method every time when schedule triggered and when file deleted
	public void onFileDelete(final File file) {
		System.out.println(file.getAbsoluteFile() + " was deleted.");
	}

	//execute the method every time when schedule triggered
	public void onStop(final FileAlterationObserver observer) {
		System.out.println("The FileListener has stopped on " + observer.getDirectory().getAbsolutePath());
	}
}
