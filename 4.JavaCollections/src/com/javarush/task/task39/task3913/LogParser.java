package com.javarush.task.task39.task3913;
import com.javarush.task.task39.task3913.query.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private static final String DATETIME_PATTERN = "d.M.yyyy H:m:s";
    private Path logDir;
    public LogParser(Path logDir) {
        this.logDir = logDir;
    }
    private List<LineLog> readFile() {
        List<LineLog> recordList = new ArrayList<>();
        try {
            for (File file : logDir.toFile().listFiles()) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".log"))
                    for (String record : Files.readAllLines(file.toPath(), Charset.defaultCharset())) {
                        recordList.add(new LineLog(record));
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recordList;
    }
    private List<LineLog> getListBetween(Date after, Date before) {
        List<LineLog> res = new ArrayList<>();
        for (LineLog l :
                readFile()) {
            if (isCompatibleDate(l.date.getTime(), after, before))
                res.add(l);
        }
        return res;
    }
    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> uniqIP = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.user.equals(user))
                uniqIP.add(l.ip);
        }
        return uniqIP;
    }
    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> uniqIP = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.event.equals(event))
                uniqIP.add(l.ip);
        }
        return uniqIP;
    }
    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> uniqIP = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.status.equals(status))
                uniqIP.add(l.ip);
        }
        return uniqIP;
    }
    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> uniqIP = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            uniqIP.add(l.ip);
        }
        return uniqIP;
    }
    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        Set<String> uniqIP = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            uniqIP.add(l.ip);
        }
        return uniqIP.size();
    }
    private boolean isCompatibleDate(long lineDateTime, Date after, Date before) {
        if (after == null && before == null) {
            return true;
        } else if (after == null) {
            if (lineDateTime <= before.getTime()) {
                return true;
            }
        } else if (before == null) {
            if (lineDateTime >= after.getTime()) {
                return true;
            }
        } else {
            if (lineDateTime >= after.getTime() && lineDateTime <= before.getTime()) {
                return true;
            }
        }
        return false;
    }
    @Override
    public Set<String> getAllUsers() {
        Set<String> allUsers = new HashSet<>();
        for (LineLog l :
                getListBetween(null, null)) {
            allUsers.add(l.user);
        }
        return allUsers;
    }
    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> allUsers = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            allUsers.add(l.user);
        }
        return allUsers.size();
    }
    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> allUsers = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.user.equals(user))
                allUsers.add(l.event);
        }
        return allUsers.size();
    }
    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> allUsers = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.ip.equals(ip))
                allUsers.add(l.user);
        }
        return allUsers;
    }
    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> allUsers = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.event == Event.LOGIN)
                allUsers.add(l.user);
        }
        return allUsers;
    }
    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> allUsers = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.event == Event.DOWNLOAD_PLUGIN)
                allUsers.add(l.user);
        }
        return allUsers;
    }
    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> allUsers = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.event == Event.WRITE_MESSAGE)
                allUsers.add(l.user);
        }
        return allUsers;
    }
    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> allUsers = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.event == Event.SOLVE_TASK)
                allUsers.add(l.user);
        }
        return allUsers;
    }
    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> allUsers = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.event == Event.SOLVE_TASK && l.task == task && l.task != null)
                allUsers.add(l.user);
        }
        return allUsers;
    }
    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> allUsers = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.event == Event.DONE_TASK)
                allUsers.add(l.user);
        }
        return allUsers;
    }
    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> allUsers = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.event == Event.DONE_TASK && l.task == task && l.task != null)
                allUsers.add(l.user);
        }
        return allUsers;
    }
    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Date firstTimeDate = null;
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.user.equals(user)
                    && l.event == Event.SOLVE_TASK
                    && l.task == task)
                if (firstTimeDate == null || firstTimeDate.after(l.date)) {
                    firstTimeDate = l.date;
                }
        }
        return firstTimeDate;
    }
    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Date firstTimeDate = null;
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.user.equals(user)
                    && l.event == Event.DONE_TASK
                    && l.task == task)
                if (firstTimeDate == null || firstTimeDate.after(l.date)) {
                    firstTimeDate = l.date;
                }
        }
        return firstTimeDate;
    }
    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.event == event && l.user.equals(user))
                dates.add(l.date);
        }
        return dates;
    }
    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.status == Status.FAILED)
                dates.add(l.date);
        }
        return dates;
    }
    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.status == Status.ERROR)
                dates.add(l.date);
        }
        return dates;
    }
    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Date firstTimeDate = null;
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.user.equals(user) && l.event == Event.LOGIN)
                if (firstTimeDate == null || firstTimeDate.after(l.date)) {
                    firstTimeDate = l.date;
                }
        }
        return firstTimeDate;
    }
    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.user.equals(user) && l.event == Event.WRITE_MESSAGE)
                dates.add(l.date);
        }
        return dates;
    }
    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.user.equals(user) && l.event == Event.DOWNLOAD_PLUGIN)
                dates.add(l.date);
        }
        return dates;
    }
    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        Set<Event> res = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            res.add(l.event);
        }
        return res.size();
    }
    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> res = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            res.add(l.event);
        }
        return res;
    }
    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> res = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.ip.equals(ip))
                res.add(l.event);
        }
        return res;
    }
    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> res = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.user.equals(user))
                res.add(l.event);
        }
        return res;
    }
    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> res = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.status.equals(Status.FAILED))
                res.add(l.event);
        }
        return res;
    }
    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> res = new HashSet<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.status.equals(Status.ERROR))
                res.add(l.event);
        }
        return res;
    }
    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int res = 0;
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.event.equals(Event.SOLVE_TASK) && l.task == task)
                res++;
        }
        return res;
    }
    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int res = 0;
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.event.equals(Event.DONE_TASK) && l.task == task)
                res++;
        }
        return res;
    }
    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> res = new HashMap<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.event.equals(Event.SOLVE_TASK)) {
                res.put(l.task, res.get(l.task) == null ? 1 : res.get(l.task) + 1);
            }
        }
        return res;
    }
    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> res = new HashMap<>();
        for (LineLog l :
                getListBetween(after, before)) {
            if (l.event.equals(Event.DONE_TASK)) {
                res.put(l.task, res.get(l.task) == null ? 1 : res.get(l.task) + 1);
            }
        }
        return res;
    }
    private Set<String> getIPs(String field, String value, Date after, Date before) {
        Set<String> res = new HashSet<>();
        for (LineLog l :
                getListBetweenFilter(after, before, field, value)) {
            res.add(l.ip);
        }
        return res;
    }
    private Set<Event> getAllEvents(String field, String value, Date after, Date before) {
        Set<Event> res = new HashSet<>();
        for (LineLog l :
                getListBetweenFilter(after, before, field, value)) {
            res.add(l.event);
        }
        return res;
    }
    private Set<String> getAllUsers(String field, String value, Date after, Date before) {
        Set<String> allUsers = new HashSet<>();
        for (LineLog l :
                getListBetweenFilter(after, before, field, value)) {
            allUsers.add(l.user);
        }
        return allUsers;
    }
    private Set<Date> getAllDates(String field, String value, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (LineLog l :
                getListBetweenFilter(after, before, field, value)) {
            dates.add(l.date);
        }
        return dates;
    }
    private Set<Status> getAllStatus(String field, String value, Date after, Date before) {
        Set<Status> res = new HashSet<>();
        for (LineLog l :
                getListBetweenFilter(after, before, field, value)) {
            res.add(l.status);
        }
        return res;
    }
    private List<LineLog> getListBetweenFilter(Date after, Date before, String field, String value) {
        List<LineLog> res = new ArrayList<>();
        for (LineLog l :
                readFile()) {
            if (isCompatibleDate(l.date.getTime(), after, before))
                if (field == null) res.add(l);
                else {
                    try {
                        Object val = fieldMatcher(field, l);
                        if (val instanceof String && val.equals(value)) res.add(l);
                        else if (val instanceof Status) {
                            if (val == Status.valueOf(value)) res.add(l);
                        } else if (val instanceof Event) {
                            if (val == Event.valueOf(value)) res.add(l);
                        } else if (val instanceof Date) {
                            DateFormat format = new SimpleDateFormat(DATETIME_PATTERN);
                            try {
                                Date date = format.parse(value);
                                if (date.equals(val)) res.add(l);
                            } catch (ParseException e) {
                            }
                        }
                    } catch (IllegalArgumentException e) {
                    }
                }
        }
        return res;
    }
    private Object fieldMatcher(String fieldName, LineLog object) {
        if (fieldName == null) return null;
        Object value = null;
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            value = field.get(object);
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
        return value;
    }
    @Override
    public Set<Object> execute(String query) {
        Set<Object> res = new HashSet<>();
        if (query == null || query.isEmpty()) return res;
        Pattern p = Pattern.compile("get (ip|user|date|event|status)( for (ip|user|date|event|status) = \"(.*?)\")?( and date between \"(.*?)\" and \"(.*?)\")?");
        Matcher m = p.matcher(query);
        String field1 = null;
        String field2 = null;
        String value2 = null;
        Date after = null;
        Date before = null;
        if (m.find()) {
            field1 = m.group(1);
            field2 = m.group(3);
            value2 = m.group(4);
            if (m.group(5) != null) {
                after = convertDateFromString(m.group(6));
                before = convertDateFromString(m.group(7));
            }
        }
        if (field1 == null) return null;
        switch (field1) {
            case "ip": {
                res.addAll(getIPs(field2, value2, after, before));
                break;
            }
            case "user": {
                res.addAll(getAllUsers(field2, value2, after, before));
                break;
            }
            case "date": {
                res.addAll(getAllDates(field2, value2, after, before));
                break;
            }
            case "event": {
                res.addAll(getAllEvents(field2, value2, after, before));
                break;
            }
            case "status": {
                res.addAll(getAllStatus(field2, value2, after, before));
                break;
            }
        }
        return res;
    }
    private Date convertDateFromString(String string) {
        if (string == null) return null;
        DateFormat format = new SimpleDateFormat(DATETIME_PATTERN);
        try {
            return format.parse(string);
        } catch (ParseException e) {
        }
        return null;
    }
    private class LineLog {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private Integer task;
        private Status status;
        public LineLog(String line) {
            String[] parted = line.split("\t");
            this.ip = parted[0];
            this.user = parted[1];
            DateFormat format = new SimpleDateFormat(DATETIME_PATTERN);
            try {
                this.date = format.parse(parted[2]);
            } catch (ParseException e) {
            }
            String[] eventParted = parted[3].split(" ");
            this.event = Event.valueOf(eventParted[0]);
            if (eventParted.length > 1)
                if (eventParted[1] != null && !eventParted[1].isEmpty())
                    this.task = Integer.parseInt(eventParted[1]);
            this.status = Status.valueOf(parted[4]);
        }
    }
}