package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int height;
	protected int maxHeight;

	protected boolean USE_MAX_HEIGHT_AS_HEIGHT;
	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		if (USE_MAX_HEIGHT_AS_HEIGHT) {
			this.height = maxHeight;
		} else {
			this.height = 1;
		}
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		if (USE_MAX_HEIGHT_AS_HEIGHT) {
			for (int i = 0; i < this.height; i++) {
				this.root.forward[i] = this.NIL;
			}
		} else {
			this.root.getForward()[0] = this.NIL;
		}
	}

	/**
	 * Metodo que gera uma altura aleatoria para ser atribuida a um novo no no
	 * metodo insert(int,V)
	 */
	private int randomLevel() {
		int randomLevel = 1;
		double random = Math.random();
		while (Math.random() <= PROBABILITY && randomLevel < maxHeight) {
			randomLevel = randomLevel + 1;
		}
		return randomLevel;
	}

	@Override
	public void insert(int key, T newValue, int height) {
		if (USE_MAX_HEIGHT_AS_HEIGHT && this.size() == 0) { // verifica se esta inserindo pela primeira vez, e se o boolean for true, vai conectar root com o NIL novamente
			this.height = this.maxHeight;
			connectRootToNil();
		}
		
		SkipListNode<T>[] update = new SkipListNode[this.height];
		
		SkipListNode<T> aux = this.root;
		for (int i = this.height - 1; i >= 0; i--) {
			while (aux.forward[i] != null && aux.forward[i].getKey() < key) {
				aux = aux.forward[i];
			}
			update[i] = aux;
		}
		
		aux = aux.forward[0];
		
		if (aux.getKey() == key) {
			aux.setValue(newValue);
		} else {
			if (!USE_MAX_HEIGHT_AS_HEIGHT && height > this.height) { // se nao usar altura maxima, ajusta
				for (int i = this.height + 1; i < height; i++) {
					update[i] = this.root;
				}
				this.height = height;
			}
			aux = new SkipListNode<T>(key, height, newValue); 
			for (int i = 0; i < height; i++) {
				aux.forward[i] = update[i].forward[i];
				update[i].forward[i] = aux;
			}
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[this.height];
		
		SkipListNode<T> aux = this.root;
		for (int i = this.height - 1; i >= 0; i--) {
			while (aux.forward[i].getKey() < key) {
				aux = aux.forward[i];
			}
			update[i] = aux;
		}
		
		aux = aux.forward[0];
		
		if (aux.getKey() == key) {
			for (int i = 0; i < this.height; i++) {
				if (update[i].forward[i] != aux) break;
				update[i].forward[i] = aux.forward[i];
			}
			
			while (!USE_MAX_HEIGHT_AS_HEIGHT && this.height > 0 && this.root.forward[this.height] == NIL) { // se nao usar altura maxima, ajusta
				this.height -= 1;
			}
		}
	}

	@Override
	public int height() {
		SkipListNode<T> aux = this.root;
		for (int i = this.height - 1; i >= 0; i--) {
			if (aux.forward[i] != this.NIL) {
				return aux.forward[i].height;
			}
		}
		return 0;
	}

	@Override
	public SkipListNode<T> search(int key) {
		
		SkipListNode<T> aux = this.root;
		for (int i = this.height - 1; i >= 0; i--) {
			while (aux.forward[i].getKey() < key) {
				aux = aux.forward[i];
			}
		}
		
		aux = aux.forward[0];
		
		if (aux.getKey() == key) {
			return aux;
		} else {
			return null;
		}
	}

	@Override
	public int size() {
		int size = 0;
		SkipListNode<T> aux = this.root.forward[0];
		while (aux != this.NIL) {
			size++;
			aux = aux.forward[0];
		}
		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T>[] array = new SkipListNode[this.size() + 2];
		
		SkipListNode<T> aux = this.root;
		
		for (int i = 0; i < array.length; i++) {
			array[i] = aux;
			aux = aux.forward[0];
		}
		
		return array;
	}

}
