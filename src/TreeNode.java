import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private TreeNode parent;
    private List<TreeNode> children = new ArrayList<>();
    private int level;
    private String nodeNo;
    private String quantity;
    private int status;

    public TreeNode() {
    }

    public TreeNode(String nodeNo) {
        this.nodeNo = nodeNo;
    }

    public TreeNode(TreeNode parent, List<TreeNode> children, int level, String nodeNo, String quantity, int status) {
        this.parent = parent;
        this.children = children;
        this.level = level;
        this.nodeNo = nodeNo;
        this.quantity = quantity;
        this.status = status;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getNodeNo() {
        return nodeNo;
    }

    public void setNodeNo(String nodeNo) {
        this.nodeNo = nodeNo;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "parent=" + parent +
                ", children=" + children +
                ", level=" + level +
                ", nodeNo='" + nodeNo + '\'' +
                ", quantity='" + quantity + '\'' +
                ", status=" + status +
                '}';
    }
}
