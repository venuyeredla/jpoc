package org.vgr.store.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BlockTest2 {
	static String blockFile=FileUtil.getPath("block.dat");
	
	
	@Before
	public void writeTest() {
		DataWriter writer=new DataWriter(blockFile,false);
		
		for(int i=0;i<10;i++) {
			Block block=new Block();
			block.write("Block and venu "+i);
			int offset=i*Block.BLOCK_SIZE;
			writer.writeBlock(offset, block);
		 }
		writer.close();
	}
	
	@Test
	public void testWriteRead() {
	  System.out.println("Writing complted and reading started");	
	}
	
	@After
   public void readTest() {
	   DataReader reader=new DataReader(blockFile);
	   for(int i=0;i<10;i++) {
		    int offset=i*Block.BLOCK_SIZE;
		    Block block=reader.readBlock(offset);
		    System.out.println("Block num:"+i+" and value is:"+block.readString());
		 }
	   reader.close();
	}

}