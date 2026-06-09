package org.qa.models;

import java.util.List;

public class Craftsman {
    private String name;
    private int level;
    private List<Resource> inventory;

    public Craftsman(String name,int level,List<Resource> inventory){
        this.name = name;
        this.level = level;
        this.inventory = inventory;
    }
    public String getName(){return name;}
    public int getLevel(){return level;}
    public List<Resource> getInventory(){return inventory;}

    public boolean hasResources(List<Resource> required){
        for (Resource req : required) {
            boolean found = false;
            for (Resource inv : inventory) {
                if (inv.getName().equals(req.getName())) {
                    if (inv.getQuantity() >= req.getQuantity()) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                return false; // Не нашли ресурс или количество недостаточное
            }
        }
        return true; // Все ресурсы найдены в достаточном количестве
    }
    public void removeResources(List<Resource> required){
        for (Resource req : required) {
            for (Resource inv : inventory) {
                if (inv.getName().equals(req.getName())) {
                    inv.decrease(req.getQuantity());
                    break;
                }
            }
        }
    }
}
