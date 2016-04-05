package loaders;

import model.AbstractBaseModel;

import java.util.Set;

/**
 * Created by william on 05/04/16.
 */
public abstract class AbstractLoader<T extends AbstractBaseModel>{
    private boolean loaded;
    private T object;
    private Set<T> objectSet;

    public AbstractLoader() {
        this.loaded = false;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isLoaded() {
        return loaded;
    }

    protected T getObject() {
        return object;
    }

    protected void setObject(T object) {
        this.object = object;
    }

    public Set<T> getObjectSet() {
        return objectSet;
    }

    public void setObjectSet(Set<T> objectSet) {
        this.objectSet = objectSet;
    }
}
