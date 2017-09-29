package intelligentProduct;

import java.util.ArrayList;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import repast.simphony.engine.schedule.ScheduledMethod;
import sharedInformation.CapabilitiesEdge;
import sharedInformation.CapabilitiesNode;
import sharedInformation.PhysicalProperty;
import sharedInformation.ResourceAgent;

public class LotProductAgent implements ProductAgent{
	
	private String name;
	
	private ArrayList<String> processDone;
	private ArrayList<PhysicalProperty> processesNeeded;
	
	private AgentBeliefModel beliefModel;
	
	public LotProductAgent(String name, ArrayList<String> processesNeeded, ResourceAgent startingResource, CapabilitiesNode startingNode){
		this.name = name;
		
		// Populate the desired physical properties
		this.processesNeeded = new ArrayList<PhysicalProperty>();
		for (String process : processesNeeded){
			this.processesNeeded.add(new PhysicalProperty(process));
		}
		
		//No processes done in the beginning
		this.processDone = new ArrayList<String>();
		
		//Create an empty agent belief model
		this.beliefModel = new AgentBeliefModel();
		
		startBidding(startingResource, startingNode);		
	}

	//================================================================================
    // Part communication
    //================================================================================
	
	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void rescheduleRequest(ResourceAgent resourceAgent, int startTime) {
		// TODO Auto-generated method stub
		
	}

	//================================================================================
    // Resource Communication
    //================================================================================
	
	@Override
	public void informEvent(CapabilitiesEdge edge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitBid(ArrayList<ResourceAgent> resourceList, int bidTime) {
		String printString = "";
		for (ResourceAgent resource : resourceList){
			printString = printString + resource.toString() + " ";
		}
		System.out.println("" + resourceList + " " + bidTime);
	}
	
	//================================================================================
    // Helper method
    //================================================================================
	
	/**
	 * Start a bidding process using the starting resource
	 * @param resource
	 * @param startingNode 
	 */
	private void startBidding(ResourceAgent resource, CapabilitiesNode startingNode) {
		PhysicalProperty property = this.processesNeeded.get(0);
		resource.teamQuery(this, property, startingNode, 0, 1000, new ArrayList<ResourceAgent>());
	}
}