package at.fhhagenberg.sqe.ecc.datastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Elevator {

	public IntegerProperty numberProperty;
	public IntegerProperty payloadProperty;
	public IntegerProperty speedProperty;
	public StringProperty doorStatusProperty;
	public BooleanProperty automaticProperty;
	
	private List<Integer> targets = new ArrayList<>();
	
	public Elevator(int number) {		
		this.numberProperty = new SimpleIntegerProperty(number);
		this.payloadProperty = new SimpleIntegerProperty(0);
		this.speedProperty = new SimpleIntegerProperty(0);
		this.doorStatusProperty = new SimpleStringProperty("Closed");
		automaticProperty = new SimpleBooleanProperty(false);
	}
	
	public int getNumber() {
		return numberProperty.get();
	}
	
	public void setPayload(int payload) {
		payloadProperty.set(payload);
	}
	
	public void setSpeed(int speed) {
		speedProperty.set(speed);
	}
	
	public void setDoorStatus(int doorStatus) {
		String doorStatusStr = "";
		
		switch(doorStatus) {
			case 1:
				doorStatusStr = "Open";
				break;
			case 2:
				doorStatusStr = "Closed";
		}
		
		doorStatusProperty.set(doorStatusStr);
	}
	
	public void getAutomatic(boolean isAutomatic) {
		automaticProperty.set(isAutomatic);
	}
	
	public boolean getAutomatic() {
		return automaticProperty.get();
	}
	
	public void insertTarget(int index, int target) {
		if(!targets.contains(target)) {
			if(targets.isEmpty() || index == -1) {
				targets.add(target);
			} else {
				targets.add(index, target);
			}
		}
	}
	
	public int getNextTarget() {
		if(!targets.isEmpty()) {
			return targets.get(0);
		} else {
			return -1;
		}
	}
	
	public void removeTargetFromList() {
		if(!targets.isEmpty()) {
			int currentFloor = targets.get(0);
			targets.remove(0);
			System.out.println("------remaining targets: before refactoring ---");
			for(int i = 0; i < targets.size(); i++) {
				System.out.print(targets.get(i));
				System.out.print(" -> ");
			}
			if(!targets.isEmpty()) {
				refactor(currentFloor, targets.get(0));
			}
			System.out.println("------remaining targets: after refactoring ---");
			for(int i = 0; i < targets.size(); i++) {
				System.out.print(targets.get(i));
				System.out.print(" -> ");
			}
			/*
			System.out.println("remaining targets: ");
			for(int i = 0; i < targets.size(); i++) {
				System.out.print(targets.get(i));
				System.out.print(" -> ");
			}
			*/
		}

	}
	
	private void refactor(int currentFloor, int nextFloor) {
		List<Integer> targetsInBetween = new ArrayList<>();
		
		for(int i = 0; i < targets.size(); i++) {
			if(currentFloor < nextFloor && targets.get(i) > currentFloor && targets.get(i) < nextFloor) {
				targetsInBetween.add(targets.get(i));
				targets.remove(i);
			} else if(currentFloor > nextFloor && targets.get(i) < currentFloor && targets.get(i) > nextFloor) {
				targetsInBetween.add(targets.get(i));
				targets.remove(i);
			}
		}
		
		if(currentFloor < nextFloor) {
			Collections.sort(targetsInBetween);
		} else {
			Collections.sort(targetsInBetween, Collections.reverseOrder());
		}
		
		targets.addAll(0, targetsInBetween);
	
		
	}
}
