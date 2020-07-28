package fr.medialo.plugins.wishingwater.core;

import fr.medialo.plugins.wishingwater.exception.LocationNotSameWorldException;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

public class WishRegion {


    public Location getL1() {
        return l1;
    }

    public Location getL2() {
        return l2;
    }

    Location l1;
    Location l2;
    String name;




    public WishRegion(Location loc1, Location loc2, String name) throws LocationNotSameWorldException {
        this.l1 = loc1;
        this.l2 = loc2;
        int x1 = Math.min(loc1.getBlockX(), loc2.getBlockX());
        int y1 = Math.min(loc1.getBlockY(), loc2.getBlockY());
        int z1 = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
        int x2 = Math.max(loc1.getBlockX(), loc2.getBlockX());
        int y2 = Math.max(loc1.getBlockY(), loc2.getBlockY());
        int z2 = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
        this.l1.setX(x1);
        this.l1.setY(y1);
        this.l1.setZ(z1);
        this.l2.setX(x2);
        this.l2.setY(y2);
        this.l2.setZ(z2);
        this.name = name;
        if ( (loc1 != null && loc2 != null) && loc1.getWorld().getUID() != loc2.getWorld().getUID()){
            throw new LocationNotSameWorldException();
        }
    }


    public void setLoc1(Location loc1) {
        this.l1 = loc1;
    }


    public void setLoc2(Location loc2) {
        this.l2 = loc2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean playerIsInRegion(Player player){
        Location loc = player.getLocation();
//        int x1 = Math.min(l1.getBlockX(), l2.getBlockX());
//        int y1 = Math.min(l1.getBlockY(), l2.getBlockY());
//        int z1 = Math.min(l1.getBlockZ(), l2.getBlockZ());
//        int x2 = Math.max(l1.getBlockX(), l2.getBlockX());
//        int y2 = Math.max(l1.getBlockY(), l2.getBlockY());
//        int z2 = Math.max(l1.getBlockZ(), l2.getBlockZ());
        int x1 = l1.getBlockX();
        int y1 = l1.getBlockY();
        int z1 = l1.getBlockZ();
        int x2 = l2.getBlockX();
        int y2 = l2.getBlockY();
        int z2 = l2.getBlockZ();
        int px = loc.getBlockX();
        int py = loc.getBlockY();
        int pz = loc.getBlockZ();
        return (x2 >= px && x1 <= px   &&    y2 >= py && y1 <= py     &&   z2 >= pz && z1 <= pz );
    }
}
