package com.anmolvedant.javaproj.controller;

import com.anmolvedant.javaproj.domain.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController()
@RequestMapping("/api")
public class BackendController {
    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);
    public static Firestore db = null;
    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";
    public static final String SECURED_TEXT = "Hello from the secured resource!";
    static {
        firebase firobj = new firebase();
        try
        {
            firobj.inapp();
            db = firobj.db;
        }
        catch(Exception e)
        {
            System.out.println("Exception Occured");
        }
    }
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "All Parameters not Found")
    public class InvalidParamsException extends Exception {
        public InvalidParamsException(String s){
            super(s);
        }
    }

    @RequestMapping(path = "/data/menu", method = RequestMethod.POST)
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String getMenuItems() throws IOException,InterruptedException, ExecutionException {
        List<item1> objList = new ArrayList<item1>();
        ApiFuture<QuerySnapshot> future1 = db.collection("menu").get();
        List<QueryDocumentSnapshot> documents = future1.get().getDocuments();
        Gson gson = new Gson();
        for (DocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(item.class));
            item obj = document.toObject(item.class);
            item1 ob = new item1();
            ob.setId(document.getId());
            ob.setCategory(obj.category);
            ob.setDesc(obj.desc);
            ob.setName(obj.name);
            ob.setPrice(obj.price);
            objList.add(ob);
        }
        String json = gson.toJson(objList);
//        LOG.info("GET called on /hello resource");
        return json;
    }
    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloindex() {

        return "Hello there";
    }
//    @GetMapping(path = "/data/menu")
//    public String dbData() throws IOException,InterruptedException, ExecutionException {
////        FileInputStream serviceAccount = new FileInputStream("./ServiceAccountKey.json");
////        FirebaseOptions options = new FirebaseOptions.Builder()
////                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
////                .build();
////        FirebaseApp.initializeApp(options);
////
////        Firestore db = FirestoreClient.getFirestore();
//        ApiFuture<QuerySnapshot> future1 = db.collection("menu").get();
//        List<QueryDocumentSnapshot> documents = future1.get().getDocuments();
//        Gson gson = new Gson();
//        String res = "[";
//        for (DocumentSnapshot document : documents) {
//            System.out.println(document.getId() + " => " + document.toObject(item.class));
//            item obj = document.toObject(item.class);
//            item1 ob = new item1();
//            ob.setId(document.getId());
//            ob.setCategory(obj.category);
//            ob.setDesc(obj.desc);
//            ob.setName(obj.name);
//            ob.setPrice(obj.price);
//            System.out.println(ob.getId());
//            String json = gson.toJson(ob);
//            System.out.println(json);
//            res = res + json + ",";
//        }
//        res = res+']';
//        LOG.info("GET called on /menu resource");
//        return res;
//    }


    @RequestMapping(path = "/data/menu/{quer}", method = RequestMethod.POST)
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String dbDatasearch(@PathVariable("quer") String quer) throws IOException,InterruptedException, ExecutionException {
        List<item1> objList = new ArrayList<item1>();
        ApiFuture<QuerySnapshot> future1 = db.collection("menu").whereEqualTo("category", quer).get();
        List<QueryDocumentSnapshot> documents = future1.get().getDocuments();
        Gson gson = new Gson();
        for (DocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(item.class));
            item obj = document.toObject(item.class);
            item1 ob = new item1();
            ob.setId(document.getId());
            ob.setCategory(obj.category);
            ob.setDesc(obj.desc);
            ob.setName(obj.name);
            ob.setPrice(obj.price);
            objList.add(ob);
        }
        String json = gson.toJson(objList);
//        LOG.info("GET called on /hello resource");
        return json;
    }

    @RequestMapping(path = "/data/menu/addItem", method = RequestMethod.POST)
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String addItem(@RequestBody item oe) throws IOException,InterruptedException, ExecutionException {
//        ApiFuture<QuerySnapshot> future1 = db.collection("orders").get();
//        List<QueryDocumentSnapshot> documents = future1.get().getDocuments();
//        Gson gson = new Gson();

//        Map<String, Object> odata = new HashMap<>();
//        odata.put("name", oe.getName());
        ApiFuture<WriteResult> future4 = db.collection("menu").document().set(oe);
        System.out.println("Update time : " + future4.get().getUpdateTime());

        return new Gson().toJson(oe);
    }

    @RequestMapping(path = "/data/menu/updateItem", method = RequestMethod.POST)
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String updateItem(@RequestBody item1 oe) throws IOException,InterruptedException, ExecutionException {
//        ApiFuture<QuerySnapshot> future1 = db.collection("orders").get();
//        List<QueryDocumentSnapshot> documents = future1.get().getDocuments();
//        Gson gson = new Gson();

//        Map<String, Object> odata = new HashMap<>();
//        odata.put("name", oe.getName());
        try {
            if(oe.category == null || oe.desc == null || oe.name == null || oe.price == 0 || oe.id == null)
            {
                throw new InvalidParamsException("Invalid Parameters");
            }
            item ob = new item();
            ob.setCategory(oe.category);
            ob.setDesc(oe.desc);
            ob.setName(oe.name);
            ob.setPrice(oe.price);
            ApiFuture<WriteResult> future4 = db.collection("menu").document(oe.getId()).set(ob);
            System.out.println("Update time : " + future4.get().getUpdateTime());

            return new Gson().toJson(ob);
        } catch (InvalidParamsException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Parameters Not Found", ex);
        }
    }

    @GetMapping(path = "/data/menu/deleteItem/{quer}")
    public String delItem(@PathVariable("quer") String quer) throws IOException,InterruptedException, ExecutionException {
//        ApiFuture<QuerySnapshot> future1 = db.collection("orders").get();
//        List<QueryDocumentSnapshot> documents = future1.get().getDocuments();
//        Gson gson = new Gson();

//        Map<String, Object> odata = new HashMap<>();
//        odata.put("name", oe.getName());
        ApiFuture<WriteResult> future4 = db.collection("menu").document(quer).delete();
        System.out.println("Update time : " + future4.get().getUpdateTime());

        return new Gson().toJson(quer);
    }

    @RequestMapping(path = "/data/createOrder", method = RequestMethod.POST)
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String createOrder(@RequestBody order oe) throws IOException,InterruptedException, ExecutionException {
//        ApiFuture<QuerySnapshot> future1 = db.collection("orders").get();
//        List<QueryDocumentSnapshot> documents = future1.get().getDocuments();
//        Gson gson = new Gson();
        try {
            if (oe.items == null || oe.name == null || oe.phone == 0) {
                throw new InvalidParamsException("Invalid Parameters");
            }
            String res = "[";
            double bill = 0.0;
            System.out.println(oe.items[0]);

            for (int x = 0; x < oe.items.length; x++) {
                DocumentReference docRef = db.collection("menu").document(oe.items[x]);
                ApiFuture<DocumentSnapshot> future = docRef.get();
                DocumentSnapshot document = future.get();
                if (document.exists()) {
                    System.out.println("Document data: " + document.getData());
                    item ob = document.toObject(item.class);
                    System.out.println(ob.name);
                    bill = bill + ob.price;
                } else {
                    System.out.println("No Document Found!");
                }
            }
            DocumentReference docRef = db.collection("appdata").document("current");
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            appdata oo = document.toObject(appdata.class);
            System.out.println(oo.orderid);
            int orderid = oo.orderid;
            Map<String, Object> odata = new HashMap<>();
            odata.put("orderid", (orderid + 1));
            ApiFuture<WriteResult> future4 = db.collection("appdata").document("current").set(odata);
            System.out.println("Update time : " + future4.get().getUpdateTime());

            //        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date();
            //        orderdb ob = new orderdb(oe, bill, timestamp, (orderid+1));
            orderdb2 ob = new orderdb2();
            ob.setBill(bill);
            ob.setOrderid((orderid + 1));
            ob.setTime(date);
            ob.setItems(oe.getItems());
            ob.setName(oe.getName());
            ob.setPhone(oe.getPhone());
            Map<String, Object> orderdata = new HashMap<>();
            orderdata.put("bill", ob.getBill());
            orderdata.put("items", ob.getItems());
            orderdata.put("name", ob.getName());
            orderdata.put("orderid", ob.getOrderid());
            orderdata.put("phone", ob.getPhone());
            orderdata.put("time", ob.getTime());
            ApiFuture<WriteResult> future2 = db.collection("orders").document().set(orderdata);
            System.out.println("Update time : " + future2.get().toString());

            ApiFuture<QuerySnapshot> future6 = db.collection("orders").whereEqualTo("orderid", ob.getOrderid()).get();
            List<QueryDocumentSnapshot> documents2 = future6.get().getDocuments();
            String docidd = "";
            for (DocumentSnapshot document2 : documents2) {
                docidd = document2.getId();
            }

            orderstatus or = new orderstatus(ob.getName(), "Order Placed", docidd);
            ApiFuture<WriteResult> future5 = db.collection("statuses").document().set(or);
            System.out.println("Update time : " + future5.get().getUpdateTime());

            ApiFuture<QuerySnapshot> future12 = db.collection("orders").whereEqualTo("orderid", ob.getOrderid()).get();
            List<QueryDocumentSnapshot> documents4 = future12.get().getDocuments();
            for (DocumentSnapshot document3 : documents4) {
                System.out.println(document3.getId());
                ob.setId(document3.getId());
            }

            //        LOG.info("GET called on /hello resource");
            return new Gson().toJson(ob);
        }
        catch(InvalidParamsException e)
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Parameters Not Found", e);
        }
    }

    @RequestMapping(path = "/data/getOrders", method = RequestMethod.POST)
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String getOrders() throws IOException,InterruptedException, ExecutionException {
        List<orderdb2> objList = new ArrayList<orderdb2>();
        ApiFuture<QuerySnapshot> future1 = db.collection("orders").get();
        List<QueryDocumentSnapshot> documents = future1.get().getDocuments();
        Gson gson = new Gson();
        for (DocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(orderdb.class));
            orderdb ob = document.toObject(orderdb.class);
            orderdb2 ob1 = new orderdb2();
            ob1.setId(document.getId());
            ob1.setBill(ob.getBill());
            ob1.setItems(ob.getItems());
            ob1.setName(ob.getName());
            ob1.setOrderid(ob.getOrderid());
            ob1.setPhone(ob.getPhone());
            ob1.setTime(ob.getTime());
            objList.add(ob1);
        }
        String json = gson.toJson(objList);
//        LOG.info("GET called on /hello resource");
        return json;
    }

    @RequestMapping(path = "/data/updateStatus", method = RequestMethod.POST)
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String updateStatus(@RequestBody orderstatus1 os1) throws IOException,InterruptedException, ExecutionException {
        try {
            if (os1.id == null || os1.name == null || os1.status == null || os1.statusid == null) {
                throw new InvalidParamsException("Invalid Parameters");
            }
            orderstatus oe = new orderstatus(os1.name, os1.status, os1.id);
//        System.out.println(os1.id);
//        System.out.println(os1.name);
//        System.out.println(os1.status);
//        System.out.println(os1.statusid);
            if(os1.status.equals("Received"))
            {
                System.out.println("Received");
                ApiFuture<WriteResult> future4 = db.collection("statuses").document(os1.statusid).delete();
                System.out.println("Update time : " + future4.get().getUpdateTime());

                return new Gson().toJson(os1);
            }
            else
            {
                System.out.println(os1.status);
                ApiFuture<WriteResult> future4 = db.collection("statuses").document(os1.statusid).set(oe);
                System.out.println("Update time : " + future4.get().getUpdateTime());

                return new Gson().toJson(os1);
            }
        }
        catch(InvalidParamsException e)
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Parameters Not Found", e);
        }


    }

    @RequestMapping(path = "/data/getStatuses", method = RequestMethod.POST)
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String getStatuses() throws IOException,InterruptedException, ExecutionException {
        List<orderstatus1> objList = new ArrayList<orderstatus1>();
        ApiFuture<QuerySnapshot> future1 = db.collection("statuses").get();
        List<QueryDocumentSnapshot> documents = future1.get().getDocuments();
        Gson gson = new Gson();
        for (DocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(orderstatus.class));
            orderstatus ob = document.toObject(orderstatus.class);
            orderstatus1 ob1 = new orderstatus1(ob.name, ob.status, ob.id, document.getId());
            objList.add(ob1);
        }
        String json = gson.toJson(objList);
//        LOG.info("GET called on /hello resource");
        return json;

    }

    @RequestMapping(path = "/data/getOrder/{quer}", method = RequestMethod.POST)
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String getOrder(@PathVariable("quer") String quer) throws IOException,InterruptedException, ExecutionException {

        DocumentReference docRef = db.collection("orders").document(quer);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        orderdb oo = document.toObject(orderdb.class);
        Gson gson=new Gson();
        return gson.toJson(oo);
    }

    @RequestMapping(path = "/data/getItems/{quer}", method = RequestMethod.POST)
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String getItems(@PathVariable("quer") String quer) throws IOException,InterruptedException, ExecutionException {
        List<item2> objList = new ArrayList<item2>();
        String ar[] = quer.split(" ");
        List<orderid> ll = new ArrayList<orderid>();
        for(String s : ar) {
            int f = 0;
            for(orderid x : ll) {
                if (x.id.equals(s)){
                    f = 1;
                    x.quantity++;
                }
            }
            if (f == 0){
                ll.add(new orderid(s, 1));
            }
        }
        for(orderid x : ll) {
            DocumentReference docRef = db.collection("menu").document(x.id);
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            item oo = document.toObject(item.class);
            item2 ob = new item2();
            ob.setQuantity(x.quantity);
            ob.setCategory(oo.category);
            ob.setDesc(oo.desc);
            ob.setName(oo.name);
            ob.setPrice(oo.price);
            ob.setId(document.getId());
            objList.add(ob);
        }
        Gson gson=new Gson();
        return gson.toJson(objList);
    }

    @RequestMapping(path = "/data/getStatus/{quer}", method = RequestMethod.POST)
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String getStatus(@PathVariable("quer") String quer) throws IOException,InterruptedException, ExecutionException {

        DocumentReference docRef = db.collection("statuses").document(quer);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        orderstatus oo = document.toObject(orderstatus.class);
        Gson gson=new Gson();
        return gson.toJson(oo);
    }

    @RequestMapping(path = "/getUser/{id}", method = RequestMethod.POST)
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String getUserById(@PathVariable("id") String id) throws IOException,InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("users").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        userdata oo = document.toObject(userdata.class);
        Gson gson=new Gson();
        return gson.toJson(oo);
    }

    // Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
    // Required because of 'mode: history' usage in frontend routing, see README for further details
    @RequestMapping(value = "{_:^(?!index\\.html|api).$}")
    public String redirectApi() {
        LOG.info("URL entered directly into the Browser, so we need to redirect...");
        return "forward:/";
    }

}