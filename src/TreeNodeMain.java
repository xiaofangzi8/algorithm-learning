import java.util.*;

public class TreeNodeMain {

    public static void main(String[] args) {
        init();
    }

    private static List<TestMain.PartBean> partBeanList = null;
    static {
        partBeanList = TestMain.testData();
    }

    public static void init(){
        if(partBeanList == null || partBeanList.isEmpty()){
            return;
        }

        Map<String,TreeNode> cacheMap = new HashMap<>();

        // 遍历节点
        partBeanList.forEach(partBean -> {
            // 1. 获取节点的No.
            String partNo = partBean.getPartNo();
            // 2. 从缓存中获取该节点对象
            TreeNode treeNode = cacheMap.getOrDefault(partNo,new TreeNode(partNo));

            // 3. 设置该节点的属性
            treeNode.setLevel(partBean.getLevel());
            treeNode.setStatus(partBean.getState());
            treeNode.setQuantity(partBean.getQuality());

            // 4. 从缓存中取出该节点的child节点
            TreeNode childNode = cacheMap.getOrDefault(partBean.getChildNo(),new TreeNode(partBean.getChildNo()));
            // 5. 将child节点加入到该节点的儿子节点list中(前提是该child节点没有被加入过，数据没有异常的情况)
            treeNode.getChildren().add(childNode);
            // 6. 将该节点和儿子节点都放入到缓存中
            cacheMap.put(partNo,treeNode);
            cacheMap.put(childNode.getNodeNo(),childNode);

            // 7. 构造树
            //childNode.setParent(treeNode);

        });

        TreeNode rootNode = cacheMap.get("CA-0000");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(rootNode);

        List<String> ans = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            List<TreeNode> list = node.getChildren();
            queue.addAll(list);
            if("N".equals(node.getQuantity()) && node.getStatus() == 1){
                if(!(node.getParent() != null && "0".equals(node.getParent().getQuantity()))){
                    ans.add(node.getNodeNo());

                }
            }else if(isValid2(node) && !ans.contains(node.getNodeNo())){
                ans.add(node.getNodeNo());
            }

        }

        System.out.println(ans);
    }

    /**
     * 情形1
     *
     * @param node
     * @return true:展示，false:不展示
     */
    public static boolean isValid2(TreeNode node){
        if(!("N".equals(node.getQuantity()) && node.getStatus() == 0)){
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        boolean allOneFlg = false;
        while (!queue.isEmpty()){
            TreeNode pollNode = queue.poll();
            if(!pollNode.getNodeNo().equals(node.getNodeNo())){
                List<TreeNode> list = pollNode.getChildren();
                queue.addAll(list);
                if(pollNode.getQuantity() == null){
                    return false;
                }
                if(pollNode.getStatus() != 1){
                    allOneFlg = true;
                }
            }
        }
        return allOneFlg ? false : true;
    }

}
