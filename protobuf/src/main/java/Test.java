import com.diy.vcp.grpc.core.HelloRequest;
import com.diy.vcp.grpc.core.HelloWorldServiceProto;
import com.google.protobuf.InvalidProtocolBufferException;

import static com.diy.vcp.grpc.core.HelloWorldServiceProto.*;

public class Test {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        HelloRequest helloRequest = HelloRequest.newBuilder()
                                                .setName("test")
                                                .setSex("0").build();
        byte[] bytes = helloRequest.toByteArray();
        HelloRequest parseFrom = HelloRequest.parseFrom(bytes);
        System.out.println(parseFrom.getName());
    }
}
