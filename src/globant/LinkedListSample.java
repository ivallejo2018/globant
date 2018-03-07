package globant;

class Node {
	int value;
    boolean carrier;
	Node next;
	
	public Node(int value){
		this.value = value;
	}
	
	public void setNext(Node next){
		this.next = next;
	}
}

public class LinkedListSample {

	Node head;
	
	public static void main(String args[]){
		LinkedListSample lls = new LinkedListSample();
		
		Node node = new Node(2);
		node.next = new Node(1);
		node.next.next = new Node(5);
		node.next.next.next = new Node(5);
		node.next.next.next.next = new Node(4);
		node.next.next.next.next.next = new Node(5);
		node.next.next.next.next.next.next = new Node(3);
		node.next.next.next.next.next.next.next = new Node(5);
		
		//Eliminar duplicados
		//Input 2->1->5->5->4->5->3->5
		//Output 2->1->5->4->3
		lls.printLinkedList(lls.removeDuplicates(node));
		
		node = new Node(1);
		node.next = new Node(2);
		node.next.next = new Node(3);
		node.next.next.next = new Node(4);
		node.next.next.next.next = new Node(5);

		//Eliminar el nodo de enmedio
		//Input 1->2->3->4->5
		//Ouput 4->5
		lls.printLinkedList(lls.removeMiddle(node.next.next));
		
		node = new Node(1);
		node.next = new Node(2);
		node.next.next = new Node(3);
		node.next.next.next = new Node(4);
		node.next.next.next.next = new Node(5);
		
		//Elementos de la posicion n al final 
		//Input 1->2->3->4->5
		//Ouput 4->5
		lls.printLinkedList(lls.findNthLastElement(node, 3));

		Node a = new Node(5);
		a.next = new Node(4);
		a.next.next = new Node(9);
		Node b = new Node(0);
		b.next = new Node(9);
		b.next.next = new Node(1);
		
		//Suma de listas ligadas
		//Input 5->4->9	0->9->1 (945+190 = 1135)
		//Ouput 5->3->1->1
		lls.printLinkedList(lls.addNumbers(a, b));
	}
	
	private void printLinkedList(Node head){
		while(head != null){
			System.out.print(head.value);
			head = head.next;
		}
		System.out.println();
	}
	
	public Node removeMiddle(Node middle){
		
		Node head = middle;
		Node prev = null;
		while(middle.next != null){
			prev = middle;
			middle.value = middle.next.value;
			middle = middle.next;
		}
		
		prev.next = null;
		
		return head;
	}
	
	private Node addDigits(int a, int b, int carrier){
		Node node = null;
		int sumDigits = a + b +  carrier;	
		if(sumDigits >= 10){
			node = new Node(sumDigits % 10);
			node.carrier = true;
		} else {
			node = new Node(sumDigits);
		}
		
		return node;
	}
	
	public Node addNumbers(Node a, Node b){
		
		int carrier = 0;
		Node tail = addDigits(a.value, b.value, carrier);
		Node head = tail;
		
		while(a.next != null && b.next != null){		
			tail.setNext(this.addDigits(a.next.value, b.next.value, tail.carrier ? 1 : 0));
			
			a = a.next;
			b = b.next;
			tail = tail.next;
		}

		if(a.next != null){
				
			while(a.next != null){
				tail.setNext(
						this.addDigits(a.next.value, 0, tail.carrier ? 1 : 0));
				
				a = a.next;
				tail = tail.next;
			}
		}
		
		if(b.next != null){
				
			while(b.next != null){
				tail.setNext(
						this.addDigits(0, b.next.value, tail.carrier ? 1 : 0));
				
				b = b.next;
				tail = tail.next;
			}
		}
		

		if(a.next == null && b.next == null && tail.carrier) {
			tail.setNext(new Node(1));
		}
		
		return head;
	}
	
	public Node findNthLastElement(Node head, int n){
		if(head == null)
			return null;
		int count = 0;	
		while(head != null){
			count++;
			if(count == n){
				return head;
			}
			head = head.next;
		}
		return null;
	}
	
	public Node removeDuplicates(Node head){
		if(head == null)
			return head;
		
		Node next = head;
		while(next != null){
			int searchValue = next.value;
			Node aux = next;
			while(aux != null){
			
				if(aux.next != null && aux.next.value == searchValue) {
					while(aux.next != null && aux.next.value == searchValue){
						Node temp = aux.next.next;
						aux.next = temp;
						temp = null;
					}					
				} else {
					aux = aux.next;	
				}
			}
			next = next.next;
		}
		
		return head;
	}	
}
