package gadgetinspector.config;

import gadgetinspector.ImplementationFinder;
import gadgetinspector.SerializableDecider;
import gadgetinspector.SlinkDiscovery;
import gadgetinspector.SourceDiscovery;
import gadgetinspector.data.ClassReference;
import gadgetinspector.data.InheritanceMap;
import gadgetinspector.data.MethodReference;
import gadgetinspector.data.MethodReference.Handle;
import gadgetinspector.javaserial.SimpleImplementationFinder;
import gadgetinspector.javaserial.SimpleSourceDiscovery;
import gadgetinspector.xstream.XstreamSerializableDecider;

import java.util.Map;
import java.util.Set;

public class XstreamDeserializationConfig implements GIConfig {
    @Override
    public String getName() {
        return "xstream";
    }

    public SerializableDecider getSerializableDecider(Map<MethodReference.Handle, MethodReference> methodMap, InheritanceMap inheritanceMap) {
        return new XstreamSerializableDecider();
    }

    @Override
    public ImplementationFinder getImplementationFinder(
        Map<Handle, MethodReference> methodMap,
        Map<Handle, Set<Handle>> methodImplMap,
        InheritanceMap inheritanceMap,
        Map<ClassReference.Handle, Set<Handle>> methodsByClass) {
        return new SimpleImplementationFinder(getSerializableDecider(methodMap, inheritanceMap), methodImplMap);
    }

    @Override
    public SourceDiscovery getSourceDiscovery() {
        return new SimpleSourceDiscovery();
    }

    @Override
    public SlinkDiscovery getSlinkDiscovery() {
        return null;
    }

}
