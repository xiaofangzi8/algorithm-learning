import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class TestMain {

    public static void main(String[] args) {
        List<PartBean> list = testData();
        System.out.println(list.size());
        System.out.println(list);

    }


    public static List<PartBean> testData() {


        List<PartBean> partList = new ArrayList<>();
        // 层级 ，No号，数量，审批完了flag
        PartBean p1 = new PartBean(0, "CA-1000", "CA-1000", 'N', 0); //表示
        PartBean p2 = new PartBean(1, "CA-1000", "CA-1010", '1', 1); //不表示
        PartBean p3 = new PartBean(1, "CA-1000", "CA-1020", 'N', 0); //不表示
        PartBean p4 = new PartBean(1, "CA-1000", "CA-1030", 'N', 0); //不表示 -> 表示
        PartBean p5 = new PartBean(2, "CA-1030", "CA-1031", '2', 1); //不表示
        PartBean p6 = new PartBean(2, "CA-1030", "CA-1032", '1', 1); //不表示
        PartBean p7 = new PartBean(2, "CA-1030", "CA-1033", '1', 1); //不表示
        PartBean p8 = new PartBean(2, "CA-1030", "CA-1034", 'N', 0); //不表示 -> 表示
        PartBean p9 = new PartBean(3, "CA-1034", "CA-1034-1", 'N', 1); //表示
        PartBean p10 = new PartBean(3, "CA-1034", "CA-1034-2", '1', 1); //不表示
        PartBean p11 = new PartBean(3, "CA-1034", "CA-1034-3", '2', 1); //不表示
        PartBean p12 = new PartBean(1, "CA-1000", "CA-1040", '2', 1); //不表示
        PartBean p13 = new PartBean(2, "CA-1040", "CA-1041", 'N', 0); //不表示
        PartBean p14 = new PartBean(2, "CA-1040", "CA-1042", 'N', 0); //不表示
        PartBean p15 = new PartBean(1, "CA-1000", "CA-1050", '2', 1); //不表示
        PartBean p16 = new PartBean(2, "CA-1050", "CA-1051", '1', 1); //不表示
        PartBean p17 = new PartBean(2, "CA-1050", "CA-1052", '1', 1); //不表示
        PartBean p18 = new PartBean(1, "CA-1000", "CA-1060", 'N', 0); //不表示 -> 表示
        PartBean p19 = new PartBean(2, "CA-1060", "CA-1061", 'N', 1); //表示
        PartBean p20 = new PartBean(2, "CA-1060", "CA-1062", 'N', 1); //表示
        partList.add(p1);
        partList.add(p2);
        partList.add(p3);
        partList.add(p4);
        partList.add(p5);
        partList.add(p6);
        partList.add(p7);
        partList.add(p8);
        partList.add(p9);
        partList.add(p10);
        partList.add(p11);
        partList.add(p12);
        partList.add(p13);
        partList.add(p14);
        partList.add(p15);
        partList.add(p16);
        partList.add(p17);
        partList.add(p18);
        partList.add(p19);
        partList.add(p20);

        Map<String, List<PartBean>> parentChildMap = new HashMap<>();
        partList.stream().forEach(partBean -> {
            String partNo = partBean.getPartNo();
            List<PartBean> children = parentChildMap.getOrDefault(partNo, new ArrayList<>());
            children.add(partBean);
            parentChildMap.put(partNo, children);
        });

        Map<Integer, List<PartBean>> levelMap = new HashMap<>();
        TreeSet<Integer> levelSet = new TreeSet<>();
        partList.stream().forEach(partBean -> {
            int level = partBean.getLevel();
            levelSet.add(level);
            List<PartBean> list = levelMap.getOrDefault(level, new ArrayList<>());
            list.add(partBean);
            levelMap.put(partBean.getLevel(), list);
        });


        TreeSet<Integer> desLevelSet = (TreeSet<Integer>) levelSet.descendingSet();

        List<PartBean> ans = new ArrayList<>();
        desLevelSet.forEach(level -> {
            List<PartBean> list = levelMap.get(level);
            final String[] tempParentNo = {null};
            list.forEach(partBean -> {

                if (partBean.getQuality() == 'N' && partBean.getState() == 1) {
                    ans.add(partBean);
                }

                if(tempParentNo[0] == null || !tempParentNo[0].equals(partBean.getPartNo()) ){
                    List<PartBean> children = parentChildMap.get(partBean.getPartNo());
                    if (children == null || children.size() == 0) {
                        return;
                    }
                    Boolean flg = true;
                    boolean flg2 = false;
                    for (PartBean partBean1 : children) {
                        if (partBean1.getState() == 1) {
                            flg2 = true;
                        }

                        if (partBean1.getQuality() == '0') {
                            flg = false;
                        }
                        tempParentNo[0] = partBean1.getPartNo();
                    }
                    if (flg && flg2) {
                        //ans.add(map.get(partBean));
                        ans.add(partBean);
                    }
                }

            });
        });
        return ans;
    }


    static class PartBean {
        int level;
        String partNo;
        char quality;
        int state;
        String childNo;

        @Override
        public String toString() {
            return "PartBean{" +
                    "level=" + level +
                    ", partNo='" + partNo + '\'' +
                    ", quality=" + quality +
                    ", state=" + state +
                    ", childNo='" + childNo + '\'' +
                    '}';
        }

        public PartBean(int l, String p, String childNo, char q, int s) {
            this.level = l;
            this.partNo = p;
            this.quality = q;
            this.state = s;
            this.childNo = childNo;
        }

        public String getPartNo() {
            return partNo;
        }

        public void setPartNo(String partNo) {
            this.partNo = partNo;
        }

        public char getQuality() {
            return quality;
        }

        public void setQuality(char quality) {
            this.quality = quality;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getChildNo() {
            return childNo;
        }

        public void setChildNo(String childNo) {
            this.childNo = childNo;
        }
    }
}
