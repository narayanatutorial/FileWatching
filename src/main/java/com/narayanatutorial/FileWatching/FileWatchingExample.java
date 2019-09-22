package com.narayanatutorial.FileWatching;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

/**
 * Hello world!
 *
 */
public class FileWatchingExample {

	// Get the user home directory to be monitored
	private static final String FOLDER = "D:/localDownload/";

	// The monitor will perform polling on the folder every 30 seconds
	private static final long pollingInterval = 3 * 1000;

	public static void main(String[] args) throws Exception {
		// Change this to match the environment you want to watch.
		final File directory = new File(FOLDER);

		// Create a new FileAlterationObserver on the given directory
		FileAlterationObserver fao = new FileAlterationObserver(directory);

		// Create a new FileAlterationListenerImpl and pass it the previously created
		// FileAlterationObserver
		fao.addListener(new FileWatchingListenerImpl());

		// Create a new FileAlterationMonitor with the given pollingInterval period
		final FileAlterationMonitor monitor = new FileAlterationMonitor(pollingInterval);

		// Add the previously created FileAlterationObserver to FileAlterationMonitor
		monitor.addObserver(fao);

		// Start the FileAlterationMonitor
		monitor.start();

		System.out.println("Starting monitor (" + FOLDER + "). \"Press CTRL+C to stop\"");
	}
}
