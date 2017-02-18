package mml.tools.util;

import mml.tools.core.MmlSubtrack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class MmlSubtrackPool {
	
	class PoolRecord {
		MmlSubtrack subtrack= MmlSubtrack.newInstance();
		Boolean flag = false;
		
		public void flipStatus(){
			this.flag = !this.flag;
		}
		public Boolean isAvailable(){
			return this.flag;
		}
		public MmlSubtrack getSubtrack(){
			return subtrack;
		}
	}
	
	List<PoolRecord> pool = new ArrayList<>();
	
	public MmlSubtrackPool(){
		
	}
	
	public MmlSubtrack checkOut(){
		Iterator<PoolRecord> iter = pool.iterator();
		PoolRecord record;
		while(iter.hasNext()){
			record = iter.next();
			if(record.isAvailable()==true){
				record.flipStatus();
				return record.getSubtrack();
			}
		}
		//No available subtrack
		record = new PoolRecord();
		pool.add(record);
		return record.getSubtrack();
	}
	
	public boolean checkIn(MmlSubtrack subtrack){
		Iterator<PoolRecord> iter = pool.iterator();
		PoolRecord record;
		while(iter.hasNext()){
			record = iter.next();
			// use == to decide whether the two sub-tracks are the same object.
			if(subtrack == record.getSubtrack() && !record.isAvailable()){
				record.flipStatus();
				return true;
			}
		}
		return false;
	}

	public List<MmlSubtrack> checkOutAll() {
		return pool.stream().map(o -> o.getSubtrack()).collect(Collectors.toList());
	}
	public static MmlSubtrackPool newInstance(){
		return new MmlSubtrackPool();
	}
}
