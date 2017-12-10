package org.vgr.store.io;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Every Read and write is done only in terms of block. Each Memory Block is considered as page.
 * @author vyeredla
 *
 */
public class DataReader implements Closeable{
	private InputStream is=null;
	private String fileName;

    public DataReader(String fileName) {
			try {
				this.fileName=fileName;
				this.is=new BufferedInputStream(new FileInputStream(fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	   }
	public DataReader(InputStream in) {
		this.is=new BufferedInputStream(in);
		this.is.mark(0);
	  }

	public Bytes readBytes(int size) {
		try {
			byte[] bytes=new byte[size];
			int count=this.is.read(bytes);
			System.out.println("No of bytes read :"+count);
			return new Bytes(bytes);
		 } catch (IOException e) {
			e.printStackTrace();
		 }
		return null;
	}
	
	
	public Bytes readBlockOld(int offset) {
		try {
			byte[] bytes=new byte[Bytes.BLOCK_SIZE];
			int count=this.is.read(bytes);
			System.out.println("No of bytes read :"+count);
			return new Bytes(bytes);
		 } catch (IOException e) {
			e.printStackTrace();
		 }
		return null;
	}
	
	public Bytes readBlock(int offset) {
		try {
		    Path path=FileSystems.getDefault().getPath(fileName);
			SeekableByteChannel sbc=Files.newByteChannel(path, StandardOpenOption.READ);
			sbc.position(offset);
		    ByteBuffer byteBuffer=ByteBuffer.allocate(Bytes.BLOCK_SIZE);
		    int  noOfbytes=sbc.read(byteBuffer);
			byte[] bytes= byteBuffer.array();
			//System.out.println("Read BufferSize : "+noOfbytes);
		    sbc.close();	
			return new Bytes(bytes);
		 } catch (IOException e) {
			e.printStackTrace();
		 }
		return null;
	}
	
	public void seek(int offset) {
		try {
			is.reset();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void close() {
		try {
				is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public InputStream getIs() {
		return is;
	}
	public void setIs(InputStream is) {
		this.is = is;
	}
	
	
}
