public class BST<T extends Comparable> //from class notes and slack
{
	private BSTNode<T> root;

	public boolean find(T item)
	{
		return find(root, item);
	}

	protected boolean find(BSTNode<T> node, T item)
	{
		if (node == null)
		{
			return false;
		}
		
		if (item.compareTo(node.data) == 0) //from https://www.leepoint.net/data/expressions/22compareobjects.html
		{
			return true;
		}
		else if (item.compareTo(node.data) < 0)
		{
			return find(node.left, item);
		}
		else
		{
			return find(node.right, item);
		}
	}

	public void insert(T item)
	{
		root = insert(root, item);
	}

	protected BSTNode insert(BSTNode<T> node, T item)
	{
		if (node == null)
		{
			return new BSTNode<T>(item);
		}
		
		if (item.compareTo(node.data) < 0)
		{
			node.left = insert(node.left, item);
		}
		else
		{
			node.right = insert(node.right, item);
		}

		return node;
	}

	public void print()
	{
		print(root);
	}

	protected void print(BSTNode<T> node)
	{
		if (node != null)
		{
			print(node.left);
			System.out.println(node.data);
			print(node.right);
		}
	}

	public void delete(T item)
	{
		root = delete(root, item);
	}

	protected BSTNode delete(BSTNode<T> node, T item)
	{
		if (node == null)
		{
			return null;
		}

		if (node.data.compareTo(item) < 0)
		{
			node.right = delete(node.right, item);
			return node;
		}
		else if (node.data.compareTo(item) > 0)
		{
			node.left = delete(node.left, item);
			return node;
		}
		else
		{
			if (node.left == null)
			{
				return node.right;
			}
			else if (node.right == null)
			{
				return node.left;
			}
			else
			{
				if (node.right.left == null)
				{
					node.data = node.right.data;
					node.right = node.right.right;
				}
				else
				{
					node.data = removeSmallest(node.right);
				}
				return node;
			}
		}
	}

	protected T removeSmallest(BSTNode<T> node)
	{
		if (node.left.left == null)
		{
			T smallest = node.left.data;
			node.left = node.left.right;
			return smallest;
		}
		else
		{
			return removeSmallest(node.left);
		}
	}
}