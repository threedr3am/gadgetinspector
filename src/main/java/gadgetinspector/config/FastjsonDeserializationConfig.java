package gadgetinspector.config;

import gadgetinspector.ImplementationFinder;
import gadgetinspector.SerializableDecider;
import gadgetinspector.SlinkDiscovery;
import gadgetinspector.SourceDiscovery;
import gadgetinspector.data.ClassReference;
import gadgetinspector.data.InheritanceMap;
import gadgetinspector.data.MethodReference;
import gadgetinspector.data.MethodReference.Handle;
import gadgetinspector.fastjson.FastjsonImplementationFinder;
import gadgetinspector.fastjson.FastjsonSerializableDecider;
import gadgetinspector.fastjson.FastjsonSourceDiscovery;
import java.util.Map;
import java.util.Set;

public class FastjsonDeserializationConfig implements GIConfig {

    @Override
    public String getName() {
        return "fastjson";
    }

    @Override
    public SerializableDecider getSerializableDecider(Map<MethodReference.Handle, MethodReference> methodMap, InheritanceMap inheritanceMap) {
        return new FastjsonSerializableDecider(methodMap);
    }

    @Override
    public ImplementationFinder getImplementationFinder(
        Map<Handle, MethodReference> methodMap,
        Map<Handle, Set<Handle>> methodImplMap,
        InheritanceMap inheritanceMap,
        Map<ClassReference.Handle, Set<Handle>> methodsByClass) {
        return new FastjsonImplementationFinder(getSerializableDecider(methodMap, inheritanceMap), methodImplMap, methodsByClass);
    }

    @Override
    public SourceDiscovery getSourceDiscovery() {
        return new FastjsonSourceDiscovery();
    }

    @Override
    public SlinkDiscovery getSlinkDiscovery() {
        return null;
    }
}
