package im.ot;

public class AnOperationTranformerCreator implements OperationTransformerCreator{

	@Override
	public OperationTransformer getOperationTransformer() {		
		return new AnOperationTransformer();
	}

}
