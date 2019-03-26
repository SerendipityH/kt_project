package com.serendipity.fast;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.serendipity.common.utils.FastDFSClient;

public class FastDfsTest {
	@Test
	public void testUpload() throws Exception {
		// 创建一个配置文件 内容tracker服务器地址
		// 使用全局对象配置文件
		ClientGlobal.init("E:/gqh/git/kt_project/kt-manager-web/src/main/resources/spring/conf/client.conf");
		// 创建一个TrackerClient对象
		TrackerClient trackerClient = new TrackerClient();
		// 通过TrackClient获得一个TrackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();
		// 创建一个StorgageServer的引用，可以是null
		StorageServer storageServer = null;
		// 创建一个StrorageClient,参数需要TrackerServer和StrorageServer
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		// 使用StorageClient上传文件。
		String[] strings = storageClient.upload_file("e:/1.jpg", "jpg", null);
		for (String string : strings) {
			System.out.println(string);
		}
	}

	@Test
	public void testFastDfsClient() throws Exception {
		FastDFSClient fastDFSClient = new FastDFSClient(
				"E:/gqh/git/kt_project/kt-manager-web/src/main/resources/spring/conf/client.conf");
		String string = fastDFSClient.uploadFile("e:/2.jpg");
		System.out.println(string);
	}
}
