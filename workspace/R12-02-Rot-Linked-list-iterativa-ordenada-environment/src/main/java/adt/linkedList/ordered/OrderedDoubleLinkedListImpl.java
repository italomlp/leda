package adt.linkedList.ordered;

import java.util.Comparator;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListNode;

/**
 * Para testar essa classe voce deve implementar seu comparador. Primeiro
 * implemente todos os métodos requeridos. Depois implemente dois comparadores
 * (com idéias opostas) e teste sua classe com eles. Dependendo do comparador
 * que você utilizar a lista funcionar como ascendente ou descendente, mas a
 * implemntação dos métodos é a mesma.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class OrderedDoubleLinkedListImpl<T> extends OrderedSingleLinkedListImpl<T> implements OrderedLinkedList<T>,
      DoubleLinkedList<T> {

   private DoubleLinkedListNode<T> last;

   public OrderedDoubleLinkedListImpl() {
      this.last = new DoubleLinkedListNode<>();
      super.setComparator(new Comparator<T>() {

         @Override
         public int compare(T o1, T o2) {
            return ((Comparable) o1).compareTo((Comparable) o2);
         }

      });
   }

   public OrderedDoubleLinkedListImpl(Comparator<T> comparator) {
      super(comparator);
      this.last = new DoubleLinkedListNode<>();
   }

   @Override
   public void insert(T element) {
      if (element != null) {

         if (isEmpty()) {
            super.head = this.last = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<T>(), null);
         } else {

            DoubleLinkedListNode<T> auxNode = (DoubleLinkedListNode<T>) super.head;

            while (!auxNode.getNext().isNIL()
                  && super.getComparator().compare(auxNode.getNext().getData(), element) < 0) {
               auxNode = (DoubleLinkedListNode<T>) auxNode.getNext();
            }

            DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>();
            newNode.setData(element);

            if (auxNode == super.head) {
               newNode.setNext(super.head);
               ((DoubleLinkedListNode<T>) super.head).setPrevious(newNode);
               super.head = newNode;
            } else {

               newNode.setNext(auxNode.getNext());
               ((DoubleLinkedListNode<T>) auxNode.getNext()).setPrevious(newNode);

               auxNode.setNext(newNode);

               if (newNode.getNext().isNIL())
                  this.last = newNode;
            }

         }
         super.size++;
      }
   }

   @Override
   public void remove(T element) {
      if (!isEmpty()) {

         if (super.head.getData().equals(element)) { // verifica se eh pra remover o primeiro elemento

            this.removeFirst();

         } else {

            DoubleLinkedListNode<T> prev = (DoubleLinkedListNode<T>) super.head;
            DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) super.head; // cast para usar variavel auxiliar

            // enquanto aux nao for nil e nao corresponder ao elemento, faca
            while (!aux.isNIL() && !aux.getData().equals(element)) {
               prev = aux;
               aux = (DoubleLinkedListNode<T>) aux.getNext();
            }

            // se achou, eh porque nao chegou ate o fim (aux nao eh nil)
            if (!aux.isNIL()) {

               if (aux == this.last) { // se eh pra remover o ultimo, faca
                  this.removeLast();

               } else {

                  prev.setNext(aux.getNext());
                  ((DoubleLinkedListNode<T>) aux.getNext()).setPrevious(prev);
                  super.size--;

               }
            }
         }
      }
   }

   /**
    * Este método faz sentido apenas se o elemento a ser inserido pode 
    * realmente ficar na primeira posição (devido a ordem)
    */
   @Override
   public void insertFirst(T element) {
      if (element != null) {
         if (isEmpty()) {
            super.head = this.last = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<T>(), null);
            super.size++;
         } else {
            if (super.getComparator().compare(element, super.head.getData()) < 0) {
               DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element,
                     (DoubleLinkedListNode<T>) super.head, null);
               ((DoubleLinkedListNode<T>) super.head).setPrevious(newNode);
               super.head = newNode;
               super.size++;
            }
         }
      }
   }

   @Override
   public void removeFirst() {
      if (!isEmpty()) {

         if (this.size() == 1)
            super.head = this.last = (DoubleLinkedListNode<T>) super.head.getNext();
         else {
            ((DoubleLinkedListNode<T>) super.head.getNext()).setPrevious(null);
            super.head = super.head.getNext();
         }

         super.size--;
      }
   }

   @Override
   public void removeLast() {
      if (!isEmpty()) {
    	  
         if (this.size() == 1)
            super.head = this.last = (DoubleLinkedListNode<T>) super.head.getNext();
         else {
            DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<>();
            nil.setPrevious(this.last.getPrevious());
            this.last.getPrevious().setNext(nil);
            this.last = this.last.getPrevious();
         }
         
         super.size--;
      }
   }

   public DoubleLinkedListNode<T> getLastNode() {
      return this.last;
   }

}
