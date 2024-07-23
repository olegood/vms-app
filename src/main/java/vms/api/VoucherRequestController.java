package vms.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/voucher/request")
public class VoucherRequestController {

    record VoucherRequest(String id, Person person, String description) {
        record Person(String id, String name) {
        }
    }

    private final Map<String, VoucherRequest> requests = new HashMap<>();

    @PostMapping
    void createVoucherRequest(@RequestBody VoucherRequest request) {
        requests.put(request.id(), request);
    }

    @GetMapping
    Collection<VoucherRequest> getVoucherRequests() {
        return requests.values();
    }

}
