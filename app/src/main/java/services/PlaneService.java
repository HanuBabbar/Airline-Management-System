package services;

import com.fasterxml.jackson.core.type.TypeReference;
import entities.Plane;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.User;

public class PlaneService {

    private List<Plane> planeList;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String PLANES_PATH = "app/src/main/resources/planes.json";

    public PlaneService() throws IOException {

        File planes = new File(PLANES_PATH);
        planeList = objectMapper.readValue(planes, new TypeReference<List<Plane>>() {
        });
    }

    public List<Plane> searchPlanes(String source, String destination){
        return planeList.stream().filter(plane -> validPlane(plane, source, destination)).collect(Collectors.toList());
    }


    public void addPlane(Plane newPlane){
        Optional<Plane> existingPlane = planeList.stream().filter(plane -> plane.getPlaneId().equalsIgnoreCase(newPlane.getPlaneId())).findFirst();

        if(existingPlane.isPresent()){
            updatePlane(newPlane);
        }else{
            planeList.add(newPlane);
            savePlaneListToFile();
        }
    }

    //update an existing plane
    public void updatePlane(Plane updatedPlane) {
        OptionalInt index = IntStream.range(0, planeList.size())
                .filter(i -> planeList.get(i).getPlaneId().equalsIgnoreCase(updatedPlane.getPlaneId()))
                .findFirst();

        if(index.isPresent()){

            planeList.set(index.getAsInt(), updatedPlane);
            savePlaneListToFile();
        }
        else{
            addPlane(updatedPlane);
        }
    }

    private void savePlaneListToFile(){
        try{
            objectMapper.writeValue(new File(PLANES_PATH), planeList);
        }catch (IOException e){
            e.printStackTrace(); //handles exception
        }
    }

    private boolean validPlane(Plane plane, String source, String destination){
        List<String> planeOrder = plane.getAirports();

        int sourceIndex = planeOrder.indexOf(source);
        int destinationIndex = planeOrder.indexOf(destination);

        return sourceIndex!= -1 && destinationIndex!= -1 && sourceIndex < destinationIndex;
    }





}
