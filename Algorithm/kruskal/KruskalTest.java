package com.ssafy.kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KruskalTest {
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	static int[] parents;
	static int V, E;
	static Edge[] edgeList;
	
	static void make() { // 크기 1인 서로 소 집합 생성
		parents = new int[V];
		for(int i=0;i<V;i++) { // 모든 노드가 자신을 부모로하는 (대표자) 집합으로 만듦
			parents[i]=i;
		}
	}
	static int find(int curr) { // a의 대표자 찾기
		if(parents[curr]==curr) return curr;
		
		return parents[curr]=find(parents[curr]); // 우리의 대표자를 나의 부모로... : path compression
	}
	static boolean union(int a, int b) { // 리턴값 : true ==> union성공
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return false;
		
		parents[bRoot] = aRoot; // a가 더 작을 때
		return true;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		edgeList = new Edge[E];
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(in.readLine()," ");
			edgeList[i] = new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		make();
		Arrays.sort(edgeList);
		
		int result = 0;
		int count =0;
		for(Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				result+= edge.weight;
				if(++count==V-1) break;
			}
		}
		System.out.println(result);
	}
}

