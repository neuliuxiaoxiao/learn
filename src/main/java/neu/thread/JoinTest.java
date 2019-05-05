/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package neu.thread;

import java.lang.Exception;
import java.lang.InterruptedException;
import java.lang.Runnable;
import java.lang.String;
import java.lang.System;
import java.lang.Thread;

/**
 * ����join�����interrupt
 */
public class JoinTest {

	public static void main(String[] args) {
		Thread threadA = new Thread(new Runnable() {
			public void run() {
				System.out.println("threadA begin run");
				for(;;){
					
				}
			}
		});
		final Thread mainThread = Thread.currentThread();
		Thread threadB = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				mainThread.interrupt();
				
			}
		});
		threadA.start();
		threadB.start();
			try {
				threadA.join();
			}
			catch (InterruptedException e) {
				System.out.println("main thread:"+e);
			}
	}
}
