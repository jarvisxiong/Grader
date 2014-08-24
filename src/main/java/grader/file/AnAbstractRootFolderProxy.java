package grader.file;

import util.misc.Common;
import grader.trace.file.load.RootFolderProxyLoaded;

import java.util.*;

public abstract class AnAbstractRootFolderProxy extends AnAbstractProxy implements RootFolderProxy {
    protected Map<String, FileProxy> nameToFileProxy = new HashMap();
    protected List<FileProxy> entries = new ArrayList();

    protected void add(FileProxy aFileProxy) {
        entries.add(aFileProxy);
        nameToFileProxy.put(aFileProxy.getAbsoluteName().toLowerCase(), aFileProxy);
    }

    @Override
    public List<FileProxy> getFileEntries() {
        return entries;
    }

    @Override
    public Set<String> getEntryNames() {
        return nameToFileProxy.keySet();
    }

    public List<FileProxy> getChildrenOf(String aParentName) {
        String myName = aParentName.toLowerCase();
        int parentDepth = Common.numMiddleOccurences(myName, '/');

        List<FileProxy> retVal = new ArrayList();
        for (FileProxy entry : entries) {
            String childName = entry.getAbsoluteName();
            if (!childName.startsWith(myName)) continue;
            int childDepth = Common.numMiddleOccurences(childName, '/');

            if (childDepth == parentDepth + 1) {
                retVal.add(entry);
            }
        }
        return retVal;

    }

    @Override
    public Set<String> getDescendentEntryNames(FileProxy aParent) {
        String parentName = aParent.getAbsoluteName();
        Set<String> allChildren = getEntryNames();
        Set<String> retVal = new HashSet();
        for (String name : allChildren) {
            if (name.startsWith(parentName))
                retVal.add(name);
        }
        return retVal;
    }

    public FileProxy getFileEntryFromLocalName(String name) {
        return getFileEntry(getAbsoluteName() + "/" + name);
    }

    public boolean isDirectory() {
        return true;
    }

    protected void initChildrenRootData() {
        for (FileProxy entry : entries) {
            entry.initRootData();
            String entryName = entry.getLocalName();
            if (entryName == null) continue;
            int index = entryName.indexOf('/');
            if (index == -1)
                childrenNames.add(entry.getAbsoluteName());
        }
        RootFolderProxyLoaded.newCase(getAbsoluteName(), this);
    }
    boolean debug;
    @Override
    public FileProxy getFileEntry(String name) {
    	String lowercase = name.toLowerCase();
//        return nameToFileProxy.get(name.toLowerCase());
    	FileProxy retVal = nameToFileProxy.get(lowercase);
    	if (retVal == null && debug) {
    		Set<String> keys = nameToFileProxy.keySet();
    		for (String key:keys) {
    			System.out.println("comparing" + key + " and\n" + lowercase);
    			if (key.equals(lowercase)) return nameToFileProxy.get(key);
    		}
    	}
    	return retVal;
    }


}
