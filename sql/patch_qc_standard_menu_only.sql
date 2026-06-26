    SET NAMES utf8mb4;

    DELETE FROM sys_role_menu WHERE menu_id IN (2100, 2101, 2102, 2103, 2104, 2105);
    DELETE FROM sys_menu WHERE menu_id IN (2100, 2101, 2102, 2103, 2104, 2105);

    INSERT INTO sys_menu VALUES(2100, CONVERT(UNHEX('E8B4A8E6A380E6A087E58786') USING utf8mb4), 0, 10, 'qcStandard', 'system/qcStandard/index', '', '', 1, 0, 'C', '0', '0', 'system:qcStandard:list', 'documentation', 'admin', sysdate(), '', null, CONVERT(UNHEX('E8B4A8E6A380E6A087E58786E8839CE58D95') USING utf8mb4));
    INSERT INTO sys_menu VALUES(2101, CONVERT(UNHEX('E8B4A8E6A380E6A087E58786E69FA5E8AFA2') USING utf8mb4), 2100, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:qcStandard:query', '#', 'admin', sysdate(), '', null, '');
    INSERT INTO sys_menu VALUES(2102, CONVERT(UNHEX('E8B4A8E6A380E6A087E58786E696B0E5A29E') USING utf8mb4), 2100, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:qcStandard:add', '#', 'admin', sysdate(), '', null, '');
    INSERT INTO sys_menu VALUES(2103, CONVERT(UNHEX('E8B4A8E6A380E6A087E58786E4BFAEE694B9') USING utf8mb4), 2100, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:qcStandard:edit', '#', 'admin', sysdate(), '', null, '');
    INSERT INTO sys_menu VALUES(2104, CONVERT(UNHEX('E8B4A8E6A380E6A087E58786E588A0E999A4') USING utf8mb4), 2100, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:qcStandard:remove', '#', 'admin', sysdate(), '', null, '');
    INSERT INTO sys_menu VALUES(2105, CONVERT(UNHEX('E8B4A8E6A380E6A087E58786E5AFBCE587BA') USING utf8mb4), 2100, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'system:qcStandard:export', '#', 'admin', sysdate(), '', null, '');

    INSERT INTO sys_role_menu VALUES (1, 2100);
    INSERT INTO sys_role_menu VALUES (1, 2101);
    INSERT INTO sys_role_menu VALUES (1, 2102);
    INSERT INTO sys_role_menu VALUES (1, 2103);
    INSERT INTO sys_role_menu VALUES (1, 2104);
    INSERT INTO sys_role_menu VALUES (1, 2105);
