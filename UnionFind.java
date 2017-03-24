

	public static class UnionFind {
		private int id[];
		private int rank[];
		private int cont;

		public int getCont() {
			return cont;
		}

		public UnionFind(int n) {
			id = new int[n];
			rank = new int[n];
			cont = n;
			for (int i = 0; i < n; i++) {
				id[i] = i;
				rank[i] = 1;
			}
		}

		public int find(int x) {
			if (id[x] != x)
				return id[x] = find(id[x]);
			return x;
		}

		public void union(int x, int y) {
			int a = find(x);
			int b = find(y);
			if (a == b)
				return;
			if (rank[a] < rank[b]) {
				id[a] = b;
			} else {
				id[b] = a;
				if (rank[a] == rank[b])
					rank[b]++;
			}
			cont--;
		}
	}
