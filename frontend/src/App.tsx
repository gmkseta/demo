import React from "react";

import {Refine} from "@pankod/refine-core";
import {ErrorComponent, notificationProvider, ReadyPage,} from "@pankod/refine-antd";
import "@pankod/refine-antd/dist/reset.css";

import routerProvider from "@pankod/refine-react-router-v6";
import {Footer, Header, Layout, OffLayoutArea, Sider, Title,} from "components/layout";
import dataProvider from "@pankod/refine-simple-rest";
import {ListDecider} from "./common/deciders/List";
import {INotice, noticeFields} from "./resources/notices/INotice";
import {AntdInferencer} from "@pankod/refine-inferencer/antd";
import {ShowDecider} from "./common/deciders/ShowDecider";
import {CreateDecider} from "./common/deciders/CreateDecider";


const { RouterComponent } = routerProvider;

const CustomRouterComponent = () => <RouterComponent basename="/app" />;


function App() {
  return (
    <Refine
      dataProvider={{
        // default: dataProvider("https://api.fake-rest.refine.dev"),
        default: dataProvider("/api")
      }}
      notificationProvider={notificationProvider}
      ReadyPage={ReadyPage}
      catchAll={<ErrorComponent />}
      resources={[
        {
          name: "notices",
          list: ListDecider<INotice>(noticeFields),
          edit: AntdInferencer,
          show: ShowDecider<INotice>(noticeFields),
          create: CreateDecider<INotice>(noticeFields),
          canDelete: true,
        },
        {
          name: "posts",
          list: AntdInferencer,
          // edit: AntdInferencer,
          // show: AntdInferencer,
          // create: AntdInferencer,
          canDelete: true
        }
      ]}
      Title={Title}
      Header={Header}
      Sider={Sider}
      Footer={Footer}
      Layout={Layout}
      OffLayoutArea={OffLayoutArea}
      routerProvider={{
        ...routerProvider,
        RouterComponent: CustomRouterComponent
      }}
    />
  );
}

export default App;
