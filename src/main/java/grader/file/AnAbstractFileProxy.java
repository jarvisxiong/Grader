package grader.file;

import util.misc.Common;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class AnAbstractFileProxy extends AnAbstractProxy implements FileProxy {
    protected RootFolderProxy rootFolderProxy;
    List<FileProxy> fileEntries;
    Set<FileProxy> childrenProxies;
    boolean isFolder;

    public AnAbstractFileProxy(RootFolderProxy aRootFolderProxy) {
        rootFolderProxy = aRootFolderProxy;
    }

    @Override
    public boolean isDirectory() {
        return getChildrenNames().size() > 0;
    }

    public FileProxy getParentFolder() {
        String parentName = Common.getParentFileName(getAbsoluteName());
        return this.getFileEntry(parentName);
    }

    public void initRootData() {
        String myName = getAbsoluteName();
        int myDepth = Common.numOccurences(myName, '/');
        descendentNames = rootFolderProxy.getDescendentEntryNames(this);
        isFolder = descendentNames.size() > 0;
        fileEntries = new ArrayList();
        for (String entryName : descendentNames) {
            int childDepth = Common.numOccurences(entryName, '/');

            if (childDepth == myDepth + 1) {
                childrenNames.add(entryName);
            }
            fileEntries.add(rootFolderProxy.getFileEntry(entryName));
        }
    }

    public List<FileProxy> getFileEntries() {
        return fileEntries;
    }

    public FileProxy getFileEntry(String name) {
        return rootFolderProxy.getFileEntry(name);
    }

    public FileProxy getFileEntryFromLocalName(String name) {
        return rootFolderProxy.getFileEntry(getAbsoluteName() + "/" + name);
    }

    public Set<String> getEntryNames() {
        return descendentNames;
    }

    public Set<String> getDescendentEntryNames(FileProxy aParent) {
        return rootFolderProxy.getDescendentEntryNames(aParent);
    }

    @Override
    public List<FileProxy> getChildrenOf(String aParentName) {
        return rootFolderProxy.getChildrenOf(aParentName);
    }
    @Override
   	public String getParentRelativeName() {
       	if (getParentFolder() == null)
       			return getLocalName();
       	else
       		return Common.toRelativeName(getParentFolder().getLocalName(), getLocalName());
   		
   	}
    
    public String toString() {
    	String retVal = getParentRelativeName();
    	if (!isDirectory()) return retVal;
    	List<FileProxy> children = getChildrenOf(getLocalName());
    	retVal += "( ";
    	
    	for (int i = 0; i < children.size(); i++) {
    		String childRepresentation = children.get(i).toString();
    		if (i == 0)
    			retVal += childRepresentation;
    		else
    			retVal += ", " + childRepresentation;
    	}
    	return retVal;
    }
}
