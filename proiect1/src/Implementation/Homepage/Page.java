package Implementation.Homepage;

public class Page {
    Page parent;
    Page children;

    public Page getChildren() {
        return children;
    }

    public void setChildren(Page children) {
        this.children = children;
    }

    private String pageName;
    public String pageDescription;

    public String getPageDescription() {
        return pageDescription;
    }

    public void setPageDescription(String pageDescription) {
        this.pageDescription = pageDescription;
    }

    public Page getParent() {
        return parent;
    }

    public void setParent(Page parent) {
        this.parent = parent;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Page() {

    }

    public Page(Page parent, Page children, String pageName, String pageDescription) {
        this.setChildren(children);
        this.setParent(parent);
        this.setPageName(pageName);
        this.setPageDescription(pageDescription);
    }
}
