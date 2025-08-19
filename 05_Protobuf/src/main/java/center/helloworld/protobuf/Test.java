package center.helloworld.protobuf;


/**
 * @author zhishun.cai
 * @date 2025/2/25
 */
public class Test {


    public static void main(String[] args) {
        PeopleInfo.Builder builder = PeopleInfo.newBuilder();
        PeopleInfo peopleInfo = builder.setName("张三").setAge(12).build();

    }
}
