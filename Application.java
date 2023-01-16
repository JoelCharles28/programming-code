import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

	class Pair{
		public Integer first;
		public Integer second;
		public Pair() {
			super();
		}
		public Pair(Integer first, Integer second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	class SortingJobs implements Comparator<Job>{

		@Override
		public int compare(Job o1, Job o2) {
			if(o1.end_time<o2.end_time) {
				return 1;
			}else {
				return 0;
			}
		}	
	}
	class Application3 {
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the number of Jobs");
			int n = sc.nextInt();
			Job[] arr=new Job[n];
			System.out.println("Enter job starts time,end time, and eanings");
			int cost;
			String start_time,end_time;
			int total=0;
			for(int i=0;i<n;i++) {
				start_time=sc.next();
				end_time=sc.next();
				if(start_time.length()<4) {
					while(start_time.length()!=4) {
						start_time+="0";
					}
				}
				if(end_time.length()<4) {
					while(end_time.length()!=4) {
						end_time+="0";
					}
				}
				cost=sc.nextInt();
				arr[i] = new Job();
				arr[i].start_time = getTime(start_time);
				arr[i].end_time = getTime(end_time);
				arr[i].cost = cost;
				total += cost;
			}
			Arrays.sort(arr,new SortingJobs());
			Pair res = new Pair();
			res = solve(arr,n);
			if(res==null) {
				System.out.println(0);
			}else {
				System.out.println("The number of tasks and earnings available for others");
				System.out.println("Task: "+(n-res.first));
				System.out.println("Earning: "+(total-res.second));
			}
			sc.close();
		}
		private static Pair solve(Job[] arr, int n) {
			if(n==0) {
				return null;
			}
			int dp[]=new int[n];
			int numOfJobs[]=new int[n];
			for(int i=0;i<n;i++) {
				dp[i]=0;
				numOfJobs[i]=0;
			}
			dp[0] = arr[0].cost;
			numOfJobs[0]=1;
			for(int i=1;i<n;i++) {
				int curr=arr[i].cost;
				int num=1;
				int idx=searchJob(arr,0,i-1,arr[i].start_time);
				if(idx!=curr && idx!=-1) {
					curr+=dp[idx];
					num+=numOfJobs[idx];
				}
				if(curr>dp[i-1]) {
					dp[i]=curr;
					numOfJobs[i]=num;
				}else {
					dp[i]=dp[i-1];
					numOfJobs[i]=numOfJobs[i-1];
				}
			}
			return new Pair(numOfJobs[n-1],dp[n-1]);
		}
		private static int searchJob(Job[] arr, int start_time, int end_time, int key) {
			int ans=-1;
			while(start_time<=end_time) {
				int mid=(start_time+end_time)/2;
				if(arr[mid].end_time<=key) {
					ans=mid;
					start_time=mid+1;
				}else {
					end_time=mid-1;
				}
			}
			return ans;
		}
		private static int getTime(String st) {
			int hr = (st.charAt(0)-'0')*10 + (st.charAt(1)-'0');
			int min = (st.charAt(2)-'0')*10 + (st.charAt(3)-'0');
			return hr*60 + min;
		}
	}
