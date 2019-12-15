//Mythili Karra
////mmkarra
////12B
////8 March 2019
import java.io.*;
import java.util.Scanner;

public class Simulation{
	public static Job getJob(Scanner in) {
		String[] s = in.nextLine().split(" ");
		int a = Integer.parseInt(s[0]);
		int d = Integer.parseInt(s[1]);
		return new Job(a, d);
	}
	public static void main(String[] args) throws IOException{
		PrintWriter report = null;    //report output file
		PrintWriter trace = null;
		Queue backup = new Queue();
		Queue finished = new Queue();
		 if(args.length < 1) {
                System.out.println("Usage: Simulation infile");
                System.exit(1);
        }
		Scanner sc = new Scanner(new File(args[0]));
		String s = sc.nextLine();
		int m = Integer.parseInt(s);
		Queue[] processorqueue = null;
		Job job;
		Queue jobs = new Queue();
		int t = 0;
		report = new PrintWriter(new FileWriter(args[0] + ".rpt"));
		trace = new PrintWriter(new FileWriter(args[0] + ".trc"));

		while(sc.hasNext()) {
			//store the jobs that are initially passed in
			backup.enqueue(getJob(sc));
		}

		trace.println("Trace file: " + (args[0] + ".trc"));
		trace.println(m + " Jobs:");
		trace.println(backup.toString());
		trace.println();

		report.println("Report file: " + (args[0] + ".rpt"));
		report.println(m + " Jobs:");
		report.println(backup.toString());
		report.println();
		report.println("***********************************************************");

		for(int n = 1; n < m; n++) { //running same simulation with diff processor count every time
			int totalWait = 0;
			int maxWait = 0;
			double avWait = 0.0;
			int k = 0;
			int time = 0;
			//reset jobs for each simulation.Ur dequeuing from jobs every time a job goes to a processor so if theres nothing in jobs at this point in the code, means its a new simulation cant mean anything else

			for(int z = 1; z < backup.length()+1; z++) { //reset every sim
				job = (Job)backup.dequeue();
				job.resetFinishTime();
				backup.enqueue(job);
				jobs.enqueue(job);
			}

			finished.dequeueAll();
			processorqueue = new Queue[n];

			for(int j = 0; j < n; j++) {
				processorqueue[j] = new Queue();
			}
			trace.println("*****************************");
			if(n==1) {
				trace.println(n + " processor:");
			}else{
				trace.println(n + " processors:");
			}
			trace.println("*****************************");

			trace.println("time=0");
			trace.println("0: " + jobs.toString());
			for(int i = 1; i < n+1; i++) {
				trace.println(i + ": " + processorqueue[i-1]);
			}
			trace.println();
			//6. Here is the processing
			Job x;
			int y = 0;
			int finish = -1;
			int index;
			Job r;
			int equal = 0;
			int twostart = 0;
			boolean test = false;
			Queue next = processorqueue[0];
			int on = 0;
			while(finished.length() != m) {
				on = 0;	
				if(!jobs.isEmpty()) {
					Job j = (Job)jobs.peek();
					int arrival = j.getArrival();
					y = 0;
					time = arrival;
				}
				for(k = 0; k < processorqueue.length;k++) {
					if(processorqueue[k].length() == 0) {
						finish = -1;

					}
					if(processorqueue[k].length() != 0) {	
						Job j = (Job)processorqueue[k].peek();
						finish = j.getFinish(); 
						if(time == finish && !jobs.isEmpty()) {
							if(on == 0) {
								equal = 1;
								y = k+1;
								on++;
							}

						}
						//if processor ends before a job begins, the next time u go to is finish time, y would equal the processor thats ending, so if y>0, then we know that we have to move the finished job to finished queue and remove it from the processor its in
						else if(finish != -1 && finish < time) {
							time = finish;
							y = k+1;
							on++;
						}
						else if(processorqueue.length > 1 && jobs.isEmpty()) {
							int rr = 0;
							int ind = 0;
							Job jj = new Job(0,0);
							Job jo;
							int fin = 0;
							Queue q;
							int one = 0;
							int two = 0;
							for(int l = 0; l < processorqueue.length; l++) {
								if(processorqueue[l].length() != 0) { 
									if(rr == 0) {
										q = processorqueue[l];
										jj = (Job)processorqueue[l].peek();
										fin = jj.getFinish();
										ind = l;
										one = l;
										rr++;
										test = false;
									}
									else {
										rr++;
										jo = (Job)processorqueue[l].peek();
										int f = jo.getFinish();
										two = l;
										if(f < fin) {
											q = processorqueue[l];
											jj = jo;
											ind = l;
											fin = f;
											one = two;
											test = false;
										}
										else if (f==fin && fin != 0) {
											test = true;
											ind = one;	
										}
									}
								}
								time = fin;
								y = ind+1;	

							}
							break;
						}
						else if(processorqueue.length == 1 && jobs.isEmpty()) {
							time = finish;
							y = k+1;
						}
					}
					}

					if(y > 0) { //this for y>0, then do if y==0
						//move the job thats done to the done queue
						if(processorqueue[y-1].length() > 0) {
							x = (Job)processorqueue[y-1].dequeue();
							finished.enqueue(x);
							int wait = x.getWaitTime();
							totalWait = totalWait + wait;
							if(wait > maxWait) {
								maxWait = wait;
							}
						}
						if(processorqueue[y-1].length() >= 1) {
							Job j = (Job)processorqueue[y-1].peek();
							j.computeFinishTime(time);

						}
						//do time stuff
					}
					else { //if arrival before finish gotta move it to best processor
						if(processorqueue.length == 1) {
							Job j = new Job(0,0);
							if(jobs.length() >=1){ 
								j = (Job)jobs.dequeue();
								processorqueue[0].enqueue(j);
								if(processorqueue[0].length() == 1) {	
									j.computeFinishTime(time);
								}
							}
							else { //if theres no new jobs to come
								if(processorqueue[0].length() > 0) {
									x = (Job)processorqueue[0].dequeue();
									finished.enqueue(x);
									if(processorqueue[0].length() > 0) {
										j = (Job)processorqueue[0].peek();
										j.computeFinishTime(time);
									}
								}
							}

						}			

						else if(processorqueue.length > 1) {
							int length = processorqueue[0].length();
							next = processorqueue[0];
							for(int i = 1; i < processorqueue.length;i++) {
								if(processorqueue[i].length() < length) {
									next = processorqueue[i];//processor with smallest line
									length = processorqueue[i].length();
									index = i;
								}
							}
							Job add = (Job)jobs.dequeue();
							next.enqueue(add);
							if(next.length() == 1) {
								add.computeFinishTime(time);

							}
							} 
						}
						//above closes else 
						Job xx;
						int in = 0;
						if(jobs.isEmpty()) {
							if(test == false) {
								trace.println("time=" + time);
								trace.println("0: " + jobs.toString()+ finished.toString());
								for(int i = 1; i < n+1; i++) {
									trace.println(i + ": " + processorqueue[i-1]);
								}
								trace.println();
							}
							test = false;
						}	
						if(!jobs.isEmpty()) { 
							xx = (Job)jobs.peek();
							int gg = xx.getArrival();
							for(int fo = 0; fo < processorqueue.length; fo++) {
								if(processorqueue[fo].length() > 0) {
									Job b = (Job)processorqueue[fo].peek();
									if(b.getFinish() == time) {
										test = true;
									}
									else if(time == gg) {
										test = true;

									}
								}
							}

							if(test == false && time != gg) {
								trace.println("time=" + time);
								trace.println("0: " + jobs.toString()+ finished.toString());
								for(int i = 1; i < n+1; i++) {
									trace.println(i + ": " + processorqueue[i-1]);
								}
								trace.println();	
							}
						}

						test = false;	

					} 
					avWait = ((double)totalWait/m);
					avWait = (double)Math.round(avWait*100)/100;
					if(n==1) {
						report.print(n + " processor: totalWait=" + totalWait + ", maxWait=" + maxWait + ", averageWait=" + String.format("%.2f", avWait));
					}else{
						report.print(n + " processors: totalWait=" + totalWait + ", maxWait=" + maxWait + ", averageWait=" + String.format("%.2f", avWait));
					}
					report.println();
					}
					sc.close();
					report.close();
					trace.close();

				}
			}

